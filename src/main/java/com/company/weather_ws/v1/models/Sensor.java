package com.company.weather_ws.v1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sensor Model object to represent the sensor database object.
 * @author Julio Dias
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensorId;
    private String description;
    private String model;
    private String brand;
    private String country;
    private String city;
    private String region;
    private Character active;
}
