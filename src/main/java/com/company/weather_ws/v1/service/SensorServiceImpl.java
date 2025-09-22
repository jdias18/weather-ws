package com.company.weather_ws.v1.service;

import com.company.weather_ws.v1.dao.SensorServiceDao;
import com.company.weather_ws.v1.models.Sensor;
import com.company.weather_ws.v1.models.SensorReadings;
import com.company.weather_ws.v1.repository.SensorReadingsRepository;
import com.company.weather_ws.v1.repository.SensorRepository;
import com.company.weather_ws.v1.representations.MetricAverageRepresentation;
import com.company.weather_ws.v1.models.MetricType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service Layer Implementation.
 * @author Julio Dias
 */
@Service
public class SensorServiceImpl implements  SensorService  {

    private final SensorRepository sensorRepository;
    private final SensorReadingsRepository sensorReadingsRepository;
    private final SensorServiceDao sensorServiceDaoImpl;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository, SensorReadingsRepository sensorReadingsRepository,
                             SensorServiceDao sensorServiceDaoImpl) {
        this.sensorRepository = sensorRepository;
        this.sensorReadingsRepository = sensorReadingsRepository;
        this.sensorServiceDaoImpl = sensorServiceDaoImpl;
    }

    public List<Sensor> findAllSensor() {
        return sensorRepository.findAll();
    }

    public List<SensorReadings> findAllSensorReadings() {
        return sensorReadingsRepository.findAll();
    }

    public Sensor addSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public SensorReadings addReading(SensorReadings sensorReadings) {
        sensorReadings.setReadDate(LocalDateTime.now());
        return sensorReadingsRepository.save(sensorReadings);
    }

    public MetricAverageRepresentation averageAllByDate(MetricType metricType, LocalDateTime startDate, LocalDateTime endDate){
        Double result = sensorServiceDaoImpl.getAvgByMetricAndDateRange(metricType, startDate, endDate);

        return MetricAverageRepresentation.builder()
                .metricType(metricType)
                .average(result)
                .build();
    }

    public MetricAverageRepresentation averageByIdAndDate(MetricType metricType, String sensorId, LocalDateTime startDate, LocalDateTime endDate){
        Double result = sensorServiceDaoImpl.getAvgByMetricSensorIdAndDateRange(metricType, sensorId, startDate, endDate);

        return MetricAverageRepresentation.builder()
                .metricType(metricType)
                .average(result)
                .build();
    }
}
