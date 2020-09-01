package cn.vector.gateway.filter;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Token校验过滤器，只对配置该过滤器的路由生效
 * Create By ahrenJ
 * Date: 2020-07-09
 */
public class TokenGatewayFilter implements GatewayFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(TokenGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authToken = exchange.getRequest().getHeaders().getFirst("AuthToken");
        if (StringUtils.isEmpty(authToken) || !authToken.equals("vector")) {
            logger.info("token校验未通过");

            ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
            objectNode.put("code", 20000);
            objectNode.put("msg", "auth fail");
            DataBuffer resp = exchange.getResponse().bufferFactory().wrap(objectNode.toString().getBytes());

            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().writeWith(Flux.just(resp));
        }
        logger.info("token校验通过");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
