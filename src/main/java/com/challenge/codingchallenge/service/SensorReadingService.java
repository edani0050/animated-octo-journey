package com.challenge.codingchallenge.service;

import com.challenge.codingchallenge.dto.SensorReadingDTO;
import com.challenge.codingchallenge.entity.SensorReading;
import com.challenge.codingchallenge.mapper.SensorReadingMapper;
import com.challenge.codingchallenge.repository.SensorReadingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.challenge.codingchallenge.constant.LoggingConstants.*;

@AllArgsConstructor
@Service
@Slf4j
public class SensorReadingService {


    private final SensorReadingRepository sensorReadingRepository;

    private static final SensorReadingMapper sensorReadingMapper = SensorReadingMapper.INSTANCE;

    public Double getAverageTemperature(LocalDateTime startDate, LocalDateTime endDate) {
        log.info(SENSOR_READING_SERVICE_GETTING_AVERAGE_TEMPERATURE_BETWEEN_AND, startDate, endDate);
        return sensorReadingRepository.findAverageTemperatureBetweenEndAndStartDate(startDate, endDate);
    }

    public Double getAverageTemperatureBySensorId(String sensorId, LocalDateTime startDate, LocalDateTime endDate) {
        log.info(SENSOR_READING_SERVICE_GETTING_AVERAGE_TEMPERATURE_FOR_SENSOR_ID_BETWEEN_AND, sensorId, startDate, endDate);
        return sensorReadingRepository.findAverageTemperatureBySensorIdBetweenEndAndStartDate(sensorId, startDate, endDate);
    }

    public SensorReading saveSensorReading(SensorReadingDTO sensorReadingDTO) {
        log.info(SENSOR_READING_SERVICE_SAVING_SENSOR_READING, sensorReadingDTO);
        return sensorReadingRepository.save(sensorReadingMapper.mapSensorReadingDTOToSenorReading(sensorReadingDTO));
    }
}
