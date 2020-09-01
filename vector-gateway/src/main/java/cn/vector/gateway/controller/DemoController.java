package cn.vector.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By ahrenJ
 * Date: 2020-07-08
 */
@RestController
@RequestMapping("/gateway")
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, spring cloud gateway!";
    }
}
