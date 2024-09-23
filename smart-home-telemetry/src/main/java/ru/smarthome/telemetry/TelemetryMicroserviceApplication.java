package ru.smarthome.telemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class TelemetryMicroserviceApplication {

    public static void main(String[] args) {
        var application = new SpringApplication(TelemetryMicroserviceApplication.class);
        application.run(args);
    }
}
