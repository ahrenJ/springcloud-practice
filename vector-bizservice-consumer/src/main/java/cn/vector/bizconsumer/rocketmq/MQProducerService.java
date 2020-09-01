package cn.vector.bizconsumer.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Create By ahrenJ
 * Date: 2020-07-12
 */
@Service
public class MQProducerService {

    private static final Logger logger = LoggerFactory.getLogger(MQProducerService.class);

    private static DefaultMQProducer producer;

    @PostConstruct
    public void init() throws MQClientException {
        producer = new DefaultMQProducer("vector_producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        logger.info("RocketMQ生产者初始化成功");
    }

    /**
     * 同步发送
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        try {
            Message mqMsg = new Message("topic_test", msg.getBytes());
            mqMsg.setKeys(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
            SendResult sendResult = producer.send(mqMsg);
            logger.info("[RocketMQ]发送消息成功: " + msg);
        } catch (Exception e) {
            logger.error("[RocketMQ]发送消息异常: " + e);
        }
    }

    /**
     * 异步发送
     *
     * @param msg
     */
    public void sendMsgAsync(String msg) throws Exception {
        Message mqMsg = new Message("topic_test", msg.getBytes());
        mqMsg.setKeys(UUID.randomUUID().toString().replace("-", "").substring(0, 12));
        producer.send(mqMsg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("[RocketMQ]发送消息成功: " + msg);
            }

            @Override
            public void onException(Throwable e) {
                logger.error("[RocketMQ]发送消息异常: " + e);
            }
        });
    }
}
