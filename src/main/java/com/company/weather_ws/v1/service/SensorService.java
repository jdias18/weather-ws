package com.company.weather_ws.v1.service;

import com.company.weather_ws.v1.models.Sensor;
import com.company.weather_ws.v1.models.SensorReadings;
import com.company.weather_ws.v1.representations.MetricAverageRepresentation;
import com.company.weather_ws.v1.models.MetricType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service Layer Interface.
 * @author Julio Dias
 */
public interface SensorService {

    public List<Sensor> findAllSensor();
    public List<SensorReadings> findAllSensorReadings();
    public Sensor addSensor(Sensor sensor);
    public SensorReadings addReading(SensorReadings sensorReadings);
    public MetricAverageRepresentation averageAllByDate(MetricType metricType, LocalDateTime startDate, LocalDateTime endDate);
    public MetricAverageRepresentation averageByIdAndDate(MetricType metricType, String sensorId, LocalDateTime startDate, LocalDateTime endDate);
}
