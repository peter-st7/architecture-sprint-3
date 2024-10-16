package ru.smarthome.kafka.acl;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.smarthome.kafka.acl.command.EnableCommand;
import ru.smarthome.kafka.acl.command.SetTemperatureCommand;

@Component
@KafkaListener(topics = "${app.kafka.topic}")
public class MessageListener {

    private final MonolithFacade monolithFacade;

    public MessageListener(MonolithFacade monolithFacade) {
        this.monolithFacade = monolithFacade;
    }

    @KafkaHandler
    public void processMessage(
            @Header(name = KafkaHeaders.RECEIVED_KEY) Long deviceId,
            EnableCommand command) {
        System.err.println(deviceId + " " + command);
        if (command.enabled()) {
            monolithFacade.turnOn(deviceId);
        } else {
            monolithFacade.turnOff(deviceId);
        }
    }

    @KafkaHandler
    public void processMessage(
            @Header(name = KafkaHeaders.RECEIVED_KEY) Long deviceId,
            SetTemperatureCommand command) {
        monolithFacade.setTargetTemperature(deviceId,
                command.temperature());
    }
}
