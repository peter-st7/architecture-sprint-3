package ru.smarthome.telemetry.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smarthome.telemetry.entity.TelemetryRecord;
import ru.smarthome.telemetry.repository.TelemetryRepository;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    private final TelemetryRepository repository;

    public TelemetryController(TelemetryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<List<TelemetryRecord>> getTelemetry(
            @PathVariable String serialNumber,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam LocalDateTime from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam LocalDateTime to) {
        return ResponseEntity.ok(
                repository.findBySerialNumberAndReceivedBetween(serialNumber,
                        from, to));
    }
}
