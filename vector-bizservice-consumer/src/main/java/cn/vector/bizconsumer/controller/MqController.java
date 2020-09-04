package cn.vector.bizconsumer.controller;

import cn.vector.bizconsumer.rocketmq.MQProducerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ahrenJ
 * @date 2020-09-04
 */
@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private MQProducerService mqProducerService;

    @GetMapping("/sendMsg")
    public String sendMsg() {
        mqProducerService.sendMsg("data-" + RandomStringUtils.randomNumeric(6));
        return "发送mq消息成功";
    }
}
