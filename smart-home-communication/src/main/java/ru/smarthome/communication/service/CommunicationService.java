package ru.smarthome.communication.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

@Service
public class CommunicationService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CommunicationService.class);

    private final SessionContainer sessionContainer;

    public CommunicationService(SessionContainer sessionContainer) {
        this.sessionContainer = sessionContainer;
    }

    public boolean isDeviceConnected(String serialNumber) {
        return sessionContainer.getSession(serialNumber).isPresent();
    }

    public void sendCommand(String serialNumber, String command)
            throws IOException {
        var sessionOpt = sessionContainer.getSession(serialNumber);
        if (sessionOpt.isEmpty()) {
            LOGGER.warn("There is no established session with device {}",
                    serialNumber);
            return;
        }

        sessionOpt.get().sendMessage(new TextMessage(command));

        LOGGER.info("Command \"{}\" was sent to device {}", command,
                serialNumber);
    }
}
