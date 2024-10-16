package ru.smarthome.communication.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;
import ru.smarthome.communication.service.SessionContainer;

@Component
public class CommandWebSocketMessageHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CommandWebSocketMessageHandler.class);

    private static final String DEVICE_SERIAL_NUMBER_QUERY_PARAMETER
            = "device_sn";

    private final SessionContainer sessionContainer;

    public CommandWebSocketMessageHandler(SessionContainer sessionContainer) {
        this.sessionContainer = sessionContainer;
    }

    @Override
    public void afterConnectionEstablished(
            WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        
        var deviceSerialNumber = getDeviceSerialNumber(session);
        sessionContainer.addSession(deviceSerialNumber, session);

        LOGGER.info("Session with device {} was created", deviceSerialNumber);
    }

    private String getDeviceSerialNumber(WebSocketSession session) {
        var uriComponents = UriComponentsBuilder.fromUri(session.getUri())
                .build();
        return Optional.of(uriComponents.getQueryParams()
                .getFirst(DEVICE_SERIAL_NUMBER_QUERY_PARAMETER))
                .orElseThrow(() -> new RuntimeException(
                "Device serial number query parameter isn't presented")
                );
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status
    ) throws Exception {
        super.afterConnectionClosed(session, status);

        var deviceSerialNumber = getDeviceSerialNumber(session);
        sessionContainer.removeSession(deviceSerialNumber);

        LOGGER.info("Session with device {} was closed", deviceSerialNumber);
    }
}
