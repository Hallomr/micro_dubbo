package com.zxk.provider.kafka;

import com.alibaba.fastjson.JSONObject;
import com.zxk.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

//@Component
public class UserKafkaProducer implements ApplicationRunner {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for(int i = 0; i <= 1000; i++){
            User u = new User();
            u.setUsername(UUID.randomUUID().toString());
            u.setPassword("123456");
            kafkaTemplate.send("user_group_t1",JSONObject.toJSONString(u));
        }
    }
}
