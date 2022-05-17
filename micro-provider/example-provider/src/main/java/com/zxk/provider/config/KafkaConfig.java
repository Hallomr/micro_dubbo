package com.zxk.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public KafkaListenerContainerFactory<?> batchFactory(ConsumerFactory consumerFactory){
        ConcurrentKafkaListenerContainerFactory<Integer,String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
//        factory.getContainerProperties().setPollTimeout(1500);
        //设置为批量消费，每个批次数量在Kafka配置参数中设置
        factory.setBatchListener(true);
        //设置手动提交ackMode
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
}
