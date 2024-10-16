package ru.smarthome.communication.controller;

import java.io.IOException;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarthome.communication.service.CommunicationService;

@RestController
@RequestMapping("/api/command")
public class CommandController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory
            .getLogger(CommandController.class);

    private final CommunicationService service;

    public CommandController(CommunicationService service) {
        this.service = service;
    }

    @PostMapping("/{serialNumber}")
    public ResponseEntity<Map<String, Object>> getTelemetry(
            @PathVariable String serialNumber,
            @RequestBody String command) {
        if (!service.isDeviceConnected(serialNumber)) {
            return ResponseEntity.ok(Map.of("status", "no-connection"));
        }

        try {
            service.sendCommand(serialNumber, command);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);

            return ResponseEntity.internalServerError()
                    .body(Map.of("status", "error",
                            "msg", "Cannot send command to the device"));
        }

        return ResponseEntity.ok(Map.of("status", "sent"));
    }
}
