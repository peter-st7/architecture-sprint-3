package ru.smarthome.monolith.acl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MonolithAclApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MonolithAclApplication.class);
        application.run(args);
    }
}
