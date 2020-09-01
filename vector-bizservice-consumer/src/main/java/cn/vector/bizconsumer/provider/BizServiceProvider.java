package cn.vector.bizconsumer.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * vector-bizservice微服务的接口
 * Create By ahrenJ
 * Date: 2020-06-09
 */
@FeignClient(value = "vector-bizservice")
public interface BizServiceProvider {

    @GetMapping("/open/hello")
    String hello();
}
