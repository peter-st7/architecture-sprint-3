package ru.smarthome.telemetry.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.smarthome.telemetry.entity.TelemetryRecord;

@Repository
public interface TelemetryRepository
        extends CrudRepository<TelemetryRecord, Long> {

    @Query("select r from TelemetryRecord r where r.serialNumber = ?1 and r.received between ?2 and ?3")
    List<TelemetryRecord> findBySerialNumberAndReceivedBetween(
            String serialNumber,
            LocalDateTime from,
            LocalDateTime to);
}
