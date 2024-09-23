package ru.smarthome.monolith.acl;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ApplicationConfiguration {

    @Value("${app.kafka.topic}")
    private String kafkaTopic;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(kafkaTopic).build();
    }

    @Bean
    public KafkaMonolithFacade kafkaMonolithFacade(
            KafkaTemplate<Long, Object> kafkaTemplate) {
        return new KafkaMonolithFacade(kafkaTopic, kafkaTemplate);
    }
}
