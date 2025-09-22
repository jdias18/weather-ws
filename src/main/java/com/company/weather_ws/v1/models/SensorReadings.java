package com.company.weather_ws.v1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * SensorReadings Model object to represent the sensor Readings database object.
 * @author Julio Dias
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SensorReadings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readingId;
    private Long sensorId;
    private BigDecimal temperature;
    private BigDecimal feelsLike;
    private BigDecimal humidityPercent;
    private BigDecimal wind;
    private BigDecimal speed;
    private BigDecimal quality;
    private BigDecimal visibility;
    private BigDecimal pressure;
    private LocalDateTime readDate;
}
