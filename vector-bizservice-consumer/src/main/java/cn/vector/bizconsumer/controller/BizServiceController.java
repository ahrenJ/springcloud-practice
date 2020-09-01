package cn.vector.bizconsumer.controller;

import cn.vector.bizconsumer.provider.BizServiceProvider;
import cn.vector.bizconsumer.rocketmq.MQProducerService;
import org.apache.commons.lang3.RandomStringUtils;
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

    @Autowired
    private MQProducerService mqProducerService;

    /**
     * 调用vector-bizservice微服务提供的接口
     *
     * @return
     */
    @GetMapping("/fetchOpenApi")
    public String fetchOpenApi() {
        return bizServiceProvider.hello();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hi, I'm bizconsumer!";
    }

    @GetMapping("/sendMsg")
    public String sendMsg() {
        mqProducerService.sendMsg("data-" + RandomStringUtils.randomNumeric(6));
        return "发送mq消息成功";
    }
}
