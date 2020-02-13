package com.message.kafka.config;

import com.message.kafka.model.Post;
import com.message.kafka.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String,Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"post-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String,String> cKCF = new ConcurrentKafkaListenerContainerFactory<>();
        cKCF.setConsumerFactory(consumerFactory());
        return cKCF;
    }

    @Bean
    public ConsumerFactory<String, Post> postConsumerFactory() {
        Map<String,Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"post-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new JsonDeserializer<>(Post.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Post> postConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String,Post> cKCF = new ConcurrentKafkaListenerContainerFactory<>();
        cKCF.setConsumerFactory(postConsumerFactory());
        return cKCF;
    }

    @Bean
    public ConsumerFactory<String, User> userConsumerFactory() {
        Map<String,Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"post-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new JsonDeserializer<>(User.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> userConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String,User> cKCF = new ConcurrentKafkaListenerContainerFactory<>();
        cKCF.setConsumerFactory(userConsumerFactory());
        return cKCF;
    }


}
