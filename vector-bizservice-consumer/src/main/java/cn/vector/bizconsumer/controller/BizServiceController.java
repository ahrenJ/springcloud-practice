package cn.vector.bizconsumer.controller;

import cn.vector.bizconsumer.provider.BizServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By ahrenJ
 * Date: 2020-06-09
 */
@RestController
@RequestMapping("/bizservice")
public class BizServiceController {

    @Autowired
    private BizServiceProvider bizServiceProvider;

    @GetMapping("/fetchOpenApi")
    public String fetchOpenApi() {
        //调用vector-bizservice微服务提供的接口
        return bizServiceProvider.hello();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hi, I'm bizconsumer!";
    }
}
