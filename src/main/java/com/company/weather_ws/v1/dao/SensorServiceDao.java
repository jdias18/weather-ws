package com.company.weather_ws.v1.dao;

import com.company.weather_ws.v1.models.MetricType;
import java.time.LocalDateTime;

/**
 * Sensor DAO Layer Interface.
 * @author Julio Dias
 */
public interface SensorServiceDao {

    public Double getAvgByMetricAndDateRange(MetricType metricType, LocalDateTime startDate, LocalDateTime endDate);
    public Double getAvgByMetricSensorIdAndDateRange(MetricType metricType, String sensorId, LocalDateTime startDate, LocalDateTime endDate);

}
