CREATE TABLE IF NOT EXISTS telemetry (
    id BIGSERIAL PRIMARY KEY,
    received timestamptz NOT NULL,
    serial_number varchar(24) NOT NULL,
    ip_address varchar(32),
    message_payload varchar(1024) NOT NULL
);
