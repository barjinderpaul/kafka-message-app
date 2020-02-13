package com.message.kafka.topic;

import com.message.kafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserTopic {
    private final String TOPIC = "kafka.topic.user";
    private final String GROUP_ID = "kafka.group.userGroup";

    private final List<User> users = new ArrayList<>();

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, containerFactory = "userConcurrentKafkaListenerContainerFactory")
    public void consumeUser(User user) {
        synchronized (users) {
            users.add(user);
        }
    }

    public List<User> getUsers() {
        return users;
    }

}
