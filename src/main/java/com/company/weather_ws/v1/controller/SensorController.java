package com.company.weather_ws.v1.controller;

import com.company.weather_ws.v1.models.Sensor;
import com.company.weather_ws.v1.models.SensorReadings;
import com.company.weather_ws.v1.representations.MetricAverageRepresentation;
import com.company.weather_ws.v1.models.MetricType;
import com.company.weather_ws.v1.service.SensorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller class to provide sensor services API.
 * @author Julio Dias
 */
@RestController
@RequestMapping("/api/v1/sensor")
public class SensorController {

    private final SensorService  sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    /**
     * Add a new sensor function.
     * @param sensor
     * @return Sensor
     */
    @PostMapping
    @RequestMapping("/addSensor")
    public Sensor addSensor(@RequestBody Sensor sensor) {
        return sensorService.addSensor(sensor);
    }

    /**
     * Add a new sensor reading function.
     * @param sensorReadings
     * @return SensorReadings
     */
    @PostMapping
    @RequestMapping("/addReading")
    public SensorReadings addReading(@RequestBody SensorReadings sensorReadings) {
        return sensorService.addReading(sensorReadings);
    }

    /**
     * Find All Sensors function.
     * @return List<Sensor>
     */
    @GetMapping
    @RequestMapping("/findAllSensor")
    public List<Sensor> findAllSensor() {
        return sensorService.findAllSensor();
    }

    /**
     * Fins All Sensor readings function.
     * @return List<SensorReadings>
     */
    @GetMapping
    @RequestMapping("/findAllSensorReadings")
    public List<SensorReadings> findAllSensorReadings() {
        return sensorService.findAllSensorReadings();
    }

    /**
     * Get average by a specific metric (e.g. TEMPERATURE)
     * along with start/end dates.
     * @param metricType
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping
    @RequestMapping("/averageAllByDate/{metricType}/{startDate}/{endDate}")
    public ResponseEntity<MetricAverageRepresentation> averageAllByDate(@PathVariable String metricType,
                                                                        @PathVariable String startDate, @PathVariable String endDate) {

        if (StringUtils.isBlank(metricType) || StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sensorService.averageAllByDate(MetricType.valueOf(metricType), LocalDateTime.parse(startDate),
                LocalDateTime.parse(endDate)));
    }

    /**
     * Get average by a specific metric (e.g. TEMPERATURE) and Sensor ID
     * along with start/end dates.
     * @param metricType
     * @param sensorId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping
    @RequestMapping("/averageByIdAndDate/{metricType}/{sensorId}/{startDate}/{endDate}")
    public ResponseEntity<MetricAverageRepresentation> averageByIdAndDate(@PathVariable String metricType, @PathVariable String sensorId,
                                                                          @PathVariable String startDate, @PathVariable String endDate) {

        if (StringUtils.isBlank(metricType) || StringUtils.isBlank(sensorId) ||
                StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sensorService.averageByIdAndDate(MetricType.valueOf(metricType), sensorId,
                LocalDateTime.parse(startDate), LocalDateTime.parse(endDate)));
    }
}