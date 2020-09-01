package cn.vector.gateway.config;

import cn.vector.gateway.filter.TokenGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关路由配置
 * 如果想控制服务只能通过网关访问，可以设置为网关对外网开放，服务走内网访问
 * 或者在路由过滤器中对header修改后再转发到下游服务，下游服务进行header校验
 * Create By ahrenJ
 * Date: 2020-07-09
 */
@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator myRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("bizservice_router", p -> p
                        .path("/gateway/bizservice/**")
                        .filters(f -> f
                                .filter(new TokenGatewayFilter())
                                .stripPrefix(1))
                        .uri("lb://vector-bizservice")
                ).build();
    }

}
