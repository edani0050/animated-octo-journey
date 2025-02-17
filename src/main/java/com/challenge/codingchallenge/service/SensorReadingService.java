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

    /**
     * Retrieves the average temperature recorded within a specified date range.
     *
     * @param startDate the start date and time of the range (inclusive)
     * @param endDate   the end date and time of the range (inclusive)
     * @return the average temperature recorded within the specified range,
     * or null if no records are found
     */
    public Double getAverageTemperature(LocalDateTime startDate, LocalDateTime endDate) {
        log.info(SENSOR_READING_SERVICE_GETTING_AVERAGE_TEMPERATURE_BETWEEN_AND, startDate, endDate);
        return sensorReadingRepository.findAverageTemperatureBetweenEndAndStartDate(startDate, endDate);
    }

    /**
     * Retrieves the average temperature recorded by a specific sensor
     * within the given date range.
     *
     * @param sensorId  the unique identifier of the sensor
     * @param startDate the start of the date and time range (inclusive)
     * @param endDate   the end of the date and time range (inclusive)
     * @return the average temperature recorded by the sensor in the specified date range, 
     * or null if no readings are found
     */
    public Double getAverageTemperatureBySensorId(String sensorId, LocalDateTime startDate, LocalDateTime endDate) {
        log.info(SENSOR_READING_SERVICE_GETTING_AVERAGE_TEMPERATURE_FOR_SENSOR_ID_BETWEEN_AND, sensorId, startDate, endDate);
        return sensorReadingRepository.findAverageTemperatureBySensorIdBetweenEndAndStartDate(sensorId, startDate, endDate);
    }


    /**
     * Saves a new sensor reading.
     * This method accepts a SensorReadingDTO, maps it to the SensorReading entity,
     * and saves it in the repository.
     *
     * @param sensorReadingDTO the Data Transfer Object containing sensor reading details
     * @return the saved SensorReading entity
     */
    public SensorReading saveSensorReading(SensorReadingDTO sensorReadingDTO) {
        log.info(SENSOR_READING_SERVICE_SAVING_SENSOR_READING, sensorReadingDTO);
        return sensorReadingRepository.save(sensorReadingMapper.mapSensorReadingDTOToSenorReading(sensorReadingDTO));
    }
}
