package cn.vector.bizconsumer.rocketmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * rocketmq push模式消费
 * Create By ahrenJ
 * Date: 2020-07-12
 */
@Component
public class MQConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MQConsumerService.class);

    private static DefaultMQPushConsumer consumer;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer("vector_consumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("topic_test", "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            try {
                list.forEach(msg -> {
                    logger.info("[RocketMQ]接收到消息: " + new String(msg.getBody()));
                });
                //logger.info("[RocketMQ]接收到消息: " + objectMapper.writeValueAsString(list));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                logger.error("[RocketMQ]消息消费异常: " + e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        //consumer.start();
        //logger.info("RocketMQ消费者初始化成功");
    }
}
