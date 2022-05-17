package com.zxk.provider.kafka;

import com.alibaba.fastjson.JSONObject;
import com.zxk.core.model.User;
import com.zxk.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserKafkaConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = {"${spring.kafka.consumer.topics}"},containerFactory="batchFactory")
    public void consumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack){
        ArrayList<User> us = new ArrayList<>();
        log.info("records size:{}",records.size());
        for (ConsumerRecord<String, String> record : records) {
            us.add(JSONObject.parseObject(record.value(),User.class));
        }
        userService.batchInsert(us);
        //手动提交
        ack.acknowledge();
    }
}
