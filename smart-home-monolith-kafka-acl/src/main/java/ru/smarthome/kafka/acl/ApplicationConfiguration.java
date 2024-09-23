package ru.smarthome.kafka.acl;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationConfiguration {

    @Value("${app.kafka.topic}")
    private String kafkaTopic;

    @Value("${app.monolith.api-url}")
    private String monolithApiUrl;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(kafkaTopic).build();
    }

    @Bean
    public MonolithFacade monolithFacade(RestClient monolithRestClient) {
        return new MonolithFacade(monolithRestClient);
    }

    @Bean
    public RestClient monolithRestClient() {
        return RestClient.builder().baseUrl(monolithApiUrl).build();
    }
}
