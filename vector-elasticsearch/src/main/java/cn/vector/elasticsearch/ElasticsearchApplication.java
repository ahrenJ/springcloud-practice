package cn.vector.elasticsearch;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.concurrent.TimeUnit;

@EnableDiscoveryClient
@SpringBootApplication
public class ElasticsearchApplication {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("logging... " + RandomStringUtils.randomNumeric(4));
            }
        }).start();
    }

}
