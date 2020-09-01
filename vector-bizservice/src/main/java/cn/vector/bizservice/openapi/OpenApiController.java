package cn.vector.bizservice.openapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 向其他微服务提供的接口
 * Create By ahrenJ
 * Date: 2020-06-09
 */
@RestController
@RequestMapping("/open")
public class OpenApiController {

    @Value("${replication}")
    private String applicationName;

    @GetMapping("/hello")
    public String hello() {
        return "Hello! I'm " + applicationName;
    }
}
