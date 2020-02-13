package com.message.kafka.controller;

import com.message.kafka.model.Post;
import com.message.kafka.model.User;
import com.message.kafka.topic.UserTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kafka")
public class KafkaUserController {
    private final String TOPIC = "kafka.topic.user";

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    UserTopic userTopic;

    @PostMapping("/users")
    public void produce (@RequestBody User user) {
        kafkaTemplate.send(TOPIC,user);
    }

    @GetMapping("users")
    public List<User> getPosts() {
        return userTopic.getUsers();
    }

}
