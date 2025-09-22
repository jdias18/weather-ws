package com.company.weather_ws.v1.controller;

import com.company.weather_ws.v1.models.MetricType;
import com.company.weather_ws.v1.models.Sensor;
import com.company.weather_ws.v1.models.SensorReadings;
import com.company.weather_ws.v1.representations.MetricAverageRepresentation;
import com.company.weather_ws.v1.service.SensorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SensorService sensorService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAddSensor() throws Exception {

        Sensor newSensorMock = new Sensor(null, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');

        Sensor savedSensorMock = new Sensor(1L, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');

        Mockito.when(sensorService.addSensor(any())).thenReturn(savedSensorMock);

        mockMvc.perform(post("/api/v1/sensor/addSensor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSensorMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sensorId").value(1L))
                .andExpect(jsonPath("$.description").value("sensor description"))
                .andExpect(jsonPath("$.model").value("sensor model"))
                .andExpect(jsonPath("$.brand").value("sensor brand"))
                .andExpect(jsonPath("$.country").value("country"))
                .andExpect(jsonPath("$.city").value("city"))
                .andExpect(jsonPath("$.region").value("region"))
                .andExpect(jsonPath("$.active").value("y"));
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

        Mockito.when(sensorService.addReading(any())).thenReturn(savedSensorReadingMock);

        mockMvc.perform(post("/api/v1/sensor/addReading")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSensorReadingMock)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.readingId").value(1L))
                .andExpect(jsonPath("$.sensorId").value(1L))
                .andExpect(jsonPath("$.temperature").value(temperature))
                .andExpect(jsonPath("$.feelsLike").value(feelsLike))
                .andExpect(jsonPath("$.humidityPercent").value(humidityPercent))
                .andExpect(jsonPath("$.wind").value(wind))
                .andExpect(jsonPath("$.speed").value(speed))
                .andExpect(jsonPath("$.quality").value(quality))
                .andExpect(jsonPath("$.visibility").value(visibility))
                .andExpect(jsonPath("$.pressure").value(pressure))
                .andExpect(jsonPath("$.readDate").value(readDate.toString()));
    }

    @Test
    void testFindAllSensor() throws Exception {

        Sensor sensorMock1 = new Sensor(1L, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');
        Sensor sensorMock2 = new Sensor(2L, "sensor description", "sensor model",
                "sensor brand", "country", "city", "region", 'y');

        List<Sensor> savedSensorList = new ArrayList<>();
        savedSensorList.add(sensorMock1);
        savedSensorList.add(sensorMock2);

        Mockito.when(sensorService.findAllSensor()).thenReturn(savedSensorList);

        mockMvc.perform(get("/api/v1/sensor/findAllSensor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))

                .andExpect(jsonPath("$[0].sensorId").value(1L))
                .andExpect(jsonPath("$[0].description").value("sensor description"))
                .andExpect(jsonPath("$[0].model").value("sensor model"))
                .andExpect(jsonPath("$[0].brand").value("sensor brand"))
                .andExpect(jsonPath("$[0].country").value("country"))
                .andExpect(jsonPath("$[0].city").value("city"))
                .andExpect(jsonPath("$[0].region").value("region"))
                .andExpect(jsonPath("$[0].active").value("y"))

                .andExpect(jsonPath("$[1].sensorId").value(2L))
                .andExpect(jsonPath("$[1].description").value("sensor description"))
                .andExpect(jsonPath("$[1].model").value("sensor model"))
                .andExpect(jsonPath("$[1].brand").value("sensor brand"))
                .andExpect(jsonPath("$[1].country").value("country"))
                .andExpect(jsonPath("$[1].city").value("city"))
                .andExpect(jsonPath("$[1].region").value("region"))
                .andExpect(jsonPath("$[1].active").value("y"));
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

        Mockito.when(sensorService.findAllSensorReadings()).thenReturn(savedSensorReadingsList);

        mockMvc.perform(get("/api/v1/sensor/findAllSensorReadings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))

                .andExpect(jsonPath("$[0].readingId").value(1L))
                .andExpect(jsonPath("$[0].sensorId").value(1L))
                .andExpect(jsonPath("$[0].temperature").value(temperature))
                .andExpect(jsonPath("$[0].feelsLike").value(feelsLike))
                .andExpect(jsonPath("$[0].humidityPercent").value(humidityPercent))
                .andExpect(jsonPath("$[0].wind").value(wind))
                .andExpect(jsonPath("$[0].speed").value(speed))
                .andExpect(jsonPath("$[0].quality").value(quality))
                .andExpect(jsonPath("$[0].visibility").value(visibility))
                .andExpect(jsonPath("$[0].pressure").value(pressure))
                .andExpect(jsonPath("$[0].readDate").value(readDate.toString()))

                .andExpect(jsonPath("$[1].readingId").value(2L))
                .andExpect(jsonPath("$[1].sensorId").value(1L))
                .andExpect(jsonPath("$[1].temperature").value(temperature))
                .andExpect(jsonPath("$[1].feelsLike").value(feelsLike))
                .andExpect(jsonPath("$[1].humidityPercent").value(humidityPercent))
                .andExpect(jsonPath("$[1].wind").value(wind))
                .andExpect(jsonPath("$[1].speed").value(speed))
                .andExpect(jsonPath("$[1].quality").value(quality))
                .andExpect(jsonPath("$[1].visibility").value(visibility))
                .andExpect(jsonPath("$[1].pressure").value(pressure))
                .andExpect(jsonPath("$[1].readDate").value(readDate.toString()));
    }

    @Test
    void testAverageAllByDate() throws Exception {

        MetricAverageRepresentation mockMetricAverageRepresentation =
                new MetricAverageRepresentation(MetricType.TEMPERATURE, 15.0);

        Mockito.when(sensorService.averageAllByDate(any(), any(), any()))
                .thenReturn(mockMetricAverageRepresentation);

        mockMvc.perform(get("/api/v1/sensor/averageAllByDate/TEMPERATURE/2025-09-17T22:32:50.113651/2025-09-20T22:32:50.113651"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metric").value("TEMPERATURE"))
                .andExpect(jsonPath("$.average").value(15.0));
    }

    @Test
    void testAverageByIdAndDate() throws Exception {

        MetricAverageRepresentation mockMetricAverageRepresentation =
                new MetricAverageRepresentation(MetricType.FEELS_LIKE, 35.0);

        Mockito.when(sensorService.averageByIdAndDate(any(), any(), any(), any()))
                .thenReturn(mockMetricAverageRepresentation);

        mockMvc.perform(get("/api/v1/sensor/averageByIdAndDate/FEELS_LIKE/1L/2025-09-17T22:32:50.113651/2025-09-20T22:32:50.113651"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metric").value("FEELS_LIKE"))
                .andExpect(jsonPath("$.average").value(35.0));
    }
}
