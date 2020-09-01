package cn.vector.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By ahrenJ
 * Date: 2020-06-19
 */
@RestController
public class TestController {

    @Value("${info}")
    private String info;

    @GetMapping("/profiles")
    public String profiles() {
        return info;
    }
}
