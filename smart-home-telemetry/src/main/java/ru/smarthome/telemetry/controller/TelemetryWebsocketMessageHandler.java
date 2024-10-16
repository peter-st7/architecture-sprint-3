package ru.smarthome.telemetry.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;
import ru.smarthome.telemetry.service.TelemetryService;

@Component
public class TelemetryWebsocketMessageHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(TelemetryWebsocketMessageHandler.class);

    private static final String DEVICE_SERIAL_NUMBER_QUERY_PARAMETER
            = "device_sn";

    private final TelemetryService telemetryService;

    public TelemetryWebsocketMessageHandler(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
            TextMessage message) throws Exception {
        var ipAddress = session.getRemoteAddress().getHostString();
        LOGGER.info("Telemetry received from {}: {}",
                ipAddress,
                message.getPayload());

        var deviceSerialNumber = getDeviceSerialNumber(session);
        telemetryService.handleTelemetryMessage(
                ipAddress,
                deviceSerialNumber,
                message.getPayload());
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
}
