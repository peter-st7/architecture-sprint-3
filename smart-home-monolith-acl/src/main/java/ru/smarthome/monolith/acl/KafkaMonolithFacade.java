package ru.smarthome.monolith.acl;

import org.springframework.kafka.core.KafkaTemplate;
import ru.smarthome.monolith.acl.command.EnableCommand;
import ru.smarthome.monolith.acl.command.SetTemperatureCommand;

public class KafkaMonolithFacade {

    private final String topicName;
    private final KafkaTemplate<Long, Object> kafkaTemplate;

    public KafkaMonolithFacade(String topicName,
            KafkaTemplate<Long, Object> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void turnOn(Long id) {
        kafkaTemplate.send(topicName, id, new EnableCommand(true));
    }

    public void turnOff(Long id) {
        kafkaTemplate.send(topicName, id, new EnableCommand(false));
    }

    public void setTargetTemperature(Long id, double temperature) {
        kafkaTemplate.send(topicName, id,
                new SetTemperatureCommand(temperature));
    }
}
