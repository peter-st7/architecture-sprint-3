package ru.smarthome.kafka.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MonolithKafkaAclApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MonolithKafkaAclApplication.class);
        application.run(args);
    }
}
