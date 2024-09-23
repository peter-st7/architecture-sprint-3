package ru.smarthome.telemetry.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import ru.smarthome.telemetry.entity.TelemetryRecord;
import ru.smarthome.telemetry.repository.TelemetryRepository;

@Service
public class TelemetryService {

    private final TelemetryRepository telemetryRepository;

    public TelemetryService(TelemetryRepository telemetryRepository) {
        this.telemetryRepository = telemetryRepository;
    }

    public void handleTelemetryMessage(String hostname,
            String deviceSerialNumber,
            String payload) {
        var telemetryRecord = new TelemetryRecord(null,
                LocalDateTime.now(),
                deviceSerialNumber,
                hostname,
                payload
        );

        saveRecord(telemetryRecord);
        sendRecordToTopic(telemetryRecord);
    }

    private void saveRecord(TelemetryRecord telemetryRecord) {
        telemetryRepository.save(telemetryRecord);
    }

    private void sendRecordToTopic(TelemetryRecord telemetryRecord) {
        // отправка сообщения в kafka
    }
}
