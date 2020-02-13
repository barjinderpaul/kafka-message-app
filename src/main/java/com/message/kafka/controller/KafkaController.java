package com.message.kafka.controller;

import com.message.kafka.model.Post;
import com.message.kafka.topic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    private final String TOPIC = "post.topic";

    @Autowired
    KafkaTemplate<String, Post> kafkaTemplate;

    @Autowired
    PostTopic postTopic;

    @PostMapping("/posts")
    public void produce (@RequestBody Post post) {
        kafkaTemplate.send(TOPIC,post);
    }

    @GetMapping("posts")
    public List<Post> getPosts() {
        return postTopic.getPosts();
    }

}
