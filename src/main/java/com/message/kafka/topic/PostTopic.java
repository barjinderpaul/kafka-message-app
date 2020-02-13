package com.message.kafka.topic;

import com.message.kafka.model.Post;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostTopic {
    private final String TOPIC = "post.topic";
    private final String GROUP_ID = "post-group";

    private final List<Post> posts = new ArrayList<>();

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, containerFactory = "postConcurrentKafkaListenerContainerFactory")
    public void consumePost(Post post) {
        synchronized (posts) {
            posts.add(post);
        }
    }

    public List<Post> getPosts() {
        return posts;
    }

}
