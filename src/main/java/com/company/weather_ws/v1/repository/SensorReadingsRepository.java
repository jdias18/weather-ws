package com.company.weather_ws.v1.repository;

import com.company.weather_ws.v1.models.SensorReadings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sensor Readings Repository to provide CRUD operations.
 * @author Julio Dias
 */
public interface SensorReadingsRepository extends JpaRepository<SensorReadings, Long> {

}
