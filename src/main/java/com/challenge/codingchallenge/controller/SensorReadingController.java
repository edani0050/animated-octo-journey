package com.challenge.codingchallenge.controller;

import com.challenge.codingchallenge.dto.SensorReadingDTO;
import com.challenge.codingchallenge.entity.SensorReading;
import com.challenge.codingchallenge.service.SensorReadingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.challenge.codingchallenge.constant.LoggingConstants.*;
import static com.challenge.codingchallenge.constant.ParameterConstants.END_PARAM;
import static com.challenge.codingchallenge.constant.ParameterConstants.START_PARAM;
import static com.challenge.codingchallenge.constant.ResponseConstant.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/sensor-readings")
public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    @PostMapping
    public ResponseEntity<String> saveSensorReading(@Valid @RequestBody SensorReadingDTO sensorReadingDTO) {
        log.info(SENSOR_READING_CONTROLLER_SAVE_SENSOR_READING);
        SensorReading sensorReading = sensorReadingService.saveSensorReading(sensorReadingDTO);
        log.info(SAVE_SUCCESSFUL);
        return ResponseEntity.ok(String.format(SENSOR_READING_SAVED_SUCCESSFULLY_FOR_SENSOR_ID_WITH_TEMPERATURE, sensorReading.getSensorId(), sensorReading.getTemperature()));
    }

    @GetMapping("/average")
    public ResponseEntity<String> getAverageTemperatureReadingByAllSensorBetweenDates(@RequestParam(START_PARAM) @Valid LocalDateTime start, @RequestParam(END_PARAM) @Valid LocalDateTime end) {
        log.info(SENSOR_READING_CONTROLLER_GET_AVERAGE_TEMPERATURE_READING_BY_ALL_SENSOR_BETWEEN_DATES);

        if (start.isAfter(end)) {
            log.warn(START_DATE_IS_AFTER_END_DATE);
            return ResponseEntity.badRequest().body(START_DATE_IS_AFTER_END_DATE);
        }

        Double averageTemperature = sensorReadingService.getAverageTemperature(start, end);
        if (averageTemperature == null) {
            log.info(NO_DATA_FOUND_FOR_REQUESTED_TIME_PERIOD);
            return ResponseEntity.ok(String.format(NO_DATA_FOUND_IN_BETWEEN_DATES_START_END, start, end));
        }

        return ResponseEntity.ok(String.format(THE_AVERAGE_TEMPERATURE_BETWEEN_AND_IS, start, end, averageTemperature));
    }

    @GetMapping("/{sensorId}/average")
    public ResponseEntity<String> getAverageTemperatureReadingBySensorIdBetweenDates(@PathVariable String sensorId, @RequestParam(START_PARAM) LocalDateTime start, @RequestParam(END_PARAM) LocalDateTime end) {
        log.info(SENSOR_READING_CONTROLLER_GET_AVERAGE_TEMPERATURE_READING_BY_SENSOR_ID_BETWEEN_DATES);

        if (start.isAfter(end)) {
            log.warn(START_DATE_IS_AFTER_END_DATE);
            return ResponseEntity.badRequest().body(START_DATE_IS_AFTER_END_DATE);
        }

        Double averageTemperatureForSensorId = sensorReadingService.getAverageTemperatureBySensorId(sensorId, start, end);
        if (averageTemperatureForSensorId == null) {
            log.info(NO_DATA_FOUND_FOR_REQUESTED_TIME_PERIOD);
            return ResponseEntity.ok(String.format(NO_DATA_FOUND_IN_BETWEEN_DATES_START_END_FOR_SENSOR_ID, start, end, sensorId));
        }

        return ResponseEntity.ok(String.format(THE_AVERAGE_TEMPERATURE_BETWEEN_AND_FOR_SENSOR_ID_IS, start, end, sensorId, averageTemperatureForSensorId));
    }

}
