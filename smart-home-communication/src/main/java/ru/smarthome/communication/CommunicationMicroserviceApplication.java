package ru.smarthome.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunicationMicroserviceApplication {

    public static void main(String[] args) {
        var application = new SpringApplication(
                CommunicationMicroserviceApplication.class);
        application.run(args);
    }
}
