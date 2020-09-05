#### SpringCloud Practice-微服务架构搭建
***
##### Eureka
- vector-eureka：注册中心
##### Feign
- vector-bizservice：服务提供
- vector-bizservice-consumer: 模拟服务调用、负载均衡
##### Config Center
- vector-config-server：配置中心
- vector-config-client：模拟从配置中心读取配置
##### Gateway
- vector-gateway：服务网关
##### Sleuth+Zipkin
- vector-bizservice-consumer：sleuth服务调用追踪、zipkin可视化
##### RocketMQ
- vector-bizservice-consumer：SpringBoot与RocketMQ集成
##### Elasticsearch
- vector-elasticsearch：SpringBoot与Elasticsearch集成