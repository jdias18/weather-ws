package com.company.weather_ws.v1.repository;

import com.company.weather_ws.v1.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sensor Repository to provide CRUD operations.
 * @author Julio Dias
 */
public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
