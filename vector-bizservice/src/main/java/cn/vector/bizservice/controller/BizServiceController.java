package cn.vector.bizservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By ahrenJ
 * Date: 2020-07-09
 */
@RestController
@RequestMapping("/bizservice")
public class BizServiceController {

    @GetMapping("/process")
    public String processBiz() {
        return "process success";
    }
}
