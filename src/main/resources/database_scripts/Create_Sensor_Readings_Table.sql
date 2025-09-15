CREATE TABLE genesys.sensor_readings
(
    sensor_id bigint NOT NULL,
    celsius integer NOT NULL,
    feelslike integer NOT NULL,
    humiditypercent integer NOT NULL,
    wind integer NOT NULL,
    speed integer NOT NULL,
    quality integer NOT NULL,
    visibility integer NOT NULL,
    pressure integer NOT NULL
);

ALTER TABLE IF EXISTS genesys.sensor_readings
    OWNER to postgres,
    ADD CONSTRAINT fk_senor_readings
    FOREIGN key (sensor_id)
    REFERENCES genesys.sensor (id);