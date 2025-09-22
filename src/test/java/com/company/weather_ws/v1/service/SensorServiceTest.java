package com.company.weather_ws.v1.service;

import com.company.weather_ws.v1.dao.SensorServiceDao;
import com.company.weather_ws.v1.models.MetricType;
import com.company.weather_ws.v1.models.Sensor;
import com.company.weather_ws.v1.models.SensorReadings;
import com.company.weather_ws.v1.repository.SensorReadingsRepository;
import com.company.weather_ws.v1.repository.SensorRepository;
import com.company.weather_ws.v1.representations.MetricAverageRepresentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class SensorServiceTest {

    @Autowired
    private SensorService SensorService;

    @MockitoBean
    private SensorRepository sensorRepository;

    @MockitoBean
    private SensorReadingsRepository sensorReadingsRepository;

    @MockitoBean
    private SensorServiceDao sensorServiceDao;
    @Autowired
    private SensorService sensorService;

    @Test
    void testFindAllSensor() throws Exception {

        Sensor sensorMock1 = new Sensor(1L, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');
        Sensor sensorMock2 = new Sensor(2L, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');

        List<Sensor> savedSensorList = new ArrayList<>();
        savedSensorList.add(sensorMock1);
        savedSensorList.add(sensorMock2);

        Mockito.when(sensorRepository.findAll()).thenReturn(savedSensorList);
        List<Sensor> result = SensorService.findAllSensor();

        Assertions.assertEquals(result.size(), savedSensorList.size());
        Assertions.assertEquals(1L, savedSensorList.get(0).getSensorId());
        Assertions.assertEquals("sensor description", savedSensorList.get(0).getDescription());
        Assertions.assertEquals("sensor model", savedSensorList.get(0).getModel());
        Assertions.assertEquals("sensor brand", savedSensorList.get(0).getBrand());
        Assertions.assertEquals("country", savedSensorList.get(0).getCountry());
        Assertions.assertEquals("city", savedSensorList.get(0).getCity());
        Assertions.assertEquals("region", savedSensorList.get(0).getRegion());
        Assertions.assertEquals('y', savedSensorList.get(0).getActive());

        Assertions.assertEquals(2L, savedSensorList.get(1).getSensorId());
        Assertions.assertEquals("sensor description", savedSensorList.get(1).getDescription());
        Assertions.assertEquals("sensor model", savedSensorList.get(1).getModel());
        Assertions.assertEquals("sensor brand", savedSensorList.get(1).getBrand());
        Assertions.assertEquals("country", savedSensorList.get(1).getCountry());
        Assertions.assertEquals("city", savedSensorList.get(1).getCity());
        Assertions.assertEquals("region", savedSensorList.get(1).getRegion());
        Assertions.assertEquals('y', savedSensorList.get(1).getActive());
    }

    @Test
    void testFindAllSensorReadings() throws Exception {

        BigDecimal temperature = new BigDecimal("40.0");
        BigDecimal feelsLike = new BigDecimal("40.0");
        BigDecimal humidityPercent = new BigDecimal("40.0");
        BigDecimal wind = new BigDecimal("40.0");
        BigDecimal speed = new BigDecimal("40.0");
        BigDecimal quality = new BigDecimal("40.0");
        BigDecimal visibility = new BigDecimal("40.0");
        BigDecimal pressure = new BigDecimal("40.0");
        LocalDateTime readDate = LocalDateTime.parse("2025-09-19T14:44:39.342492");

        SensorReadings mockSensorReadings1 = new SensorReadings(1L, 1L, temperature, feelsLike,
                humidityPercent, wind, speed, quality, visibility, pressure, readDate);
        SensorReadings mockSensorReadings2 = new SensorReadings(2L, 1L, temperature, feelsLike,
                humidityPercent, wind, speed, quality, visibility, pressure, readDate);

        List<SensorReadings> savedSensorReadingsList = new ArrayList<>();
        savedSensorReadingsList.add(mockSensorReadings1);
        savedSensorReadingsList.add(mockSensorReadings2);


        Mockito.when(sensorReadingsRepository.findAll()).thenReturn(savedSensorReadingsList);
        List<SensorReadings> result = SensorService.findAllSensorReadings();

        Assertions.assertEquals(result.size(), savedSensorReadingsList.size());
        Assertions.assertEquals(1L, savedSensorReadingsList.get(0).getReadingId());
        Assertions.assertEquals(1L, savedSensorReadingsList.get(0).getSensorId());
        Assertions.assertEquals(temperature, savedSensorReadingsList.get(0).getTemperature());
        Assertions.assertEquals(feelsLike, savedSensorReadingsList.get(0).getFeelsLike());
        Assertions.assertEquals(humidityPercent, savedSensorReadingsList.get(0).getHumidityPercent());
        Assertions.assertEquals(wind, savedSensorReadingsList.get(0).getWind());
        Assertions.assertEquals(speed, savedSensorReadingsList.get(0).getSpeed());
        Assertions.assertEquals(quality, savedSensorReadingsList.get(0).getQuality());
        Assertions.assertEquals(visibility, savedSensorReadingsList.get(0).getVisibility());
        Assertions.assertEquals(pressure, savedSensorReadingsList.get(0).getPressure());
        Assertions.assertEquals(readDate, savedSensorReadingsList.get(0).getReadDate());

        Assertions.assertEquals(result.size(), savedSensorReadingsList.size());
        Assertions.assertEquals(2L, savedSensorReadingsList.get(1).getReadingId());
        Assertions.assertEquals(1L, savedSensorReadingsList.get(1).getSensorId());
        Assertions.assertEquals(temperature, savedSensorReadingsList.get(1).getTemperature());
        Assertions.assertEquals(feelsLike, savedSensorReadingsList.get(1).getFeelsLike());
        Assertions.assertEquals(humidityPercent, savedSensorReadingsList.get(1).getHumidityPercent());
        Assertions.assertEquals(wind, savedSensorReadingsList.get(1).getWind());
        Assertions.assertEquals(speed, savedSensorReadingsList.get(1).getSpeed());
        Assertions.assertEquals(quality, savedSensorReadingsList.get(1).getQuality());
        Assertions.assertEquals(visibility, savedSensorReadingsList.get(1).getVisibility());
        Assertions.assertEquals(pressure, savedSensorReadingsList.get(1).getPressure());
        Assertions.assertEquals(readDate, savedSensorReadingsList.get(1).getReadDate());
    }

    @Test
    void testAddSensor() throws Exception {

        Sensor newSensorMock = new Sensor(null, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');

        Sensor savedSensorMock = new Sensor(1L, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');

        Mockito.when(sensorRepository.save(any())).thenReturn(savedSensorMock);
        Sensor result = SensorService.addSensor(newSensorMock);

        Assertions.assertEquals(1L, result.getSensorId());
        Assertions.assertEquals("sensor description", result.getDescription());
        Assertions.assertEquals("sensor model", result.getModel());
        Assertions.assertEquals("sensor brand", result.getBrand());
        Assertions.assertEquals("country", result.getCountry());
        Assertions.assertEquals("city", result.getCity());
        Assertions.assertEquals("region", result.getRegion());
        Assertions.assertEquals('y', result.getActive());
    }

    @Test
    void testAddReading() throws Exception {

        BigDecimal temperature = new BigDecimal("40.0");
        BigDecimal feelsLike = new BigDecimal("40.0");
        BigDecimal humidityPercent = new BigDecimal("40.0");
        BigDecimal wind = new BigDecimal("40.0");
        BigDecimal speed = new BigDecimal("40.0");
        BigDecimal quality = new BigDecimal("40.0");
        BigDecimal visibility = new BigDecimal("40.0");
        BigDecimal pressure = new BigDecimal("40.0");
        LocalDateTime readDate = LocalDateTime.parse("2025-09-19T14:44:39.342492");

        SensorReadings newSensorReadingMock = new SensorReadings(null, 1L, temperature, feelsLike, humidityPercent,
                wind, speed, quality, visibility, pressure, null);

        SensorReadings savedSensorReadingMock = new SensorReadings(1L, 1L, temperature, feelsLike, humidityPercent,
                wind, speed, quality, visibility, pressure, readDate);

        Mockito.when(sensorReadingsRepository.save(any())).thenReturn(savedSensorReadingMock);
        SensorReadings result = SensorService.addReading(newSensorReadingMock);

        Assertions.assertEquals(1L, result.getReadingId());
        Assertions.assertEquals(1L, result.getSensorId());
        Assertions.assertEquals(temperature, result.getTemperature());
        Assertions.assertEquals(feelsLike, result.getFeelsLike());
        Assertions.assertEquals(humidityPercent, result.getHumidityPercent());
        Assertions.assertEquals(wind, result.getWind());
        Assertions.assertEquals(speed, result.getSpeed());
        Assertions.assertEquals(quality, result.getQuality());
        Assertions.assertEquals(visibility, result.getVisibility());
        Assertions.assertEquals(pressure, result.getPressure());
        Assertions.assertEquals(readDate, result.getReadDate());
    }

    @Test
    void testAverageAllByDate() throws Exception {

        LocalDateTime startDate = LocalDateTime.parse("2025-09-18T14:44:39.342492");
        LocalDateTime endDate = LocalDateTime.parse("2025-09-20T14:44:39.342492");

        Mockito.when(sensorServiceDao.getAvgByMetricAndDateRange(any(), any(), any())).thenReturn(15.0);
        MetricAverageRepresentation result = sensorService.averageAllByDate(MetricType.TEMPERATURE, startDate, endDate);

        Assertions.assertEquals(MetricType.TEMPERATURE, result.getMetricType());
        Assertions.assertEquals(15.0, result.getAverage());
    }

    @Test
    void testAverageByIdAndDate() throws Exception {

        LocalDateTime startDate = LocalDateTime.parse("2025-09-18T14:44:39.342492");
        LocalDateTime endDate = LocalDateTime.parse("2025-09-20T14:44:39.342492");

        Mockito.when(sensorServiceDao.getAvgByMetricSensorIdAndDateRange(any(), any(), any(), any())).thenReturn(15.0);
        MetricAverageRepresentation result = sensorService.averageByIdAndDate(MetricType.FEELS_LIKE,"1", startDate, endDate);

        Assertions.assertEquals(MetricType.FEELS_LIKE, result.getMetricType());
        Assertions.assertEquals(15.0, result.getAverage());
    }

}