package ru.smarthome.kafka.acl;

import org.springframework.web.client.RestClient;

public class MonolithFacade {

    private final RestClient monolithRestClient;

    public MonolithFacade(RestClient monolithRestClient) {
        this.monolithRestClient = monolithRestClient;
    }

    public void turnOn(Long id) {
        monolithRestClient.post()
                .uri("/{id}/turn-on", id)
                .retrieve()
                .toBodilessEntity();
    }

    public void turnOff(Long id) {
        monolithRestClient.post()
                .uri("/{id}/turn-off", id)
                .retrieve()
                .toBodilessEntity();
    }

    public void setTargetTemperature(Long id, double temperature) {
        monolithRestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/{id}/set-temperature")
                .queryParam("temperature", temperature)
                .build(id))
                .retrieve()
                .toBodilessEntity();
    }
}
