package ru.smarthome.monolith.acl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/heating")
public class HeatingController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(HeatingController.class);

    private final KafkaMonolithFacade kafkaMonolithFacade;

    public HeatingController(KafkaMonolithFacade kafkaMonolithFacade) {
        this.kafkaMonolithFacade = kafkaMonolithFacade;
    }

    @PostMapping("/{id}/enable")
    public void turnOn(@PathVariable("id") Long id,
            @RequestParam("enabled") boolean enabled) {
        if (enabled) {
            LOGGER.info("Модуль управления отоплением с Id {} включен", id);
            kafkaMonolithFacade.turnOn(id);
        } else {
            LOGGER.info("Модуль управления отоплением с Id {} отключен", id);
            kafkaMonolithFacade.turnOff(id);
        }
    }

    @PostMapping("/{id}/temperature")
    public void setTemperature(@PathVariable("id") Long id,
            @RequestParam double temperature) {
        LOGGER.info("Установка температуры {} управляющему модулю с Id {}",
                temperature, id);
        kafkaMonolithFacade.setTargetTemperature(id, temperature);
    }
}
