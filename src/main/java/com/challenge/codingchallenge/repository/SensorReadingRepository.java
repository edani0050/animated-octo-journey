package com.challenge.codingchallenge.repository;

import com.challenge.codingchallenge.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {

    /**
     * Finds the average temperature for a given sensor id within the specified date range.
     *
     * @param sensorId The ID of the sensor to filter the readings.
     * @param start    The start date and time of the range.
     * @param end      The end date and time of the range.
     * @return The average temperature for the sensor within the range, or null if no data is found.
     */
    @Query("SELECT AVG(temperature) FROM SensorReading WHERE sensorId = ?1 AND timestamp BETWEEN ?2 AND ?3")
    Double findAverageTemperatureBySensorIdBetweenEndAndStartDate(String sensorId, LocalDateTime start, LocalDateTime end);

    /**
     * Finds the average temperature across all sensors within the specified date range.
     *
     * @param start The start date and time of the range.
     * @param end   The end date and time of the range.
     * @return The average temperature within the range, or null if no data is found.
     */
    @Query("SELECT AVG(temperature) FROM SensorReading WHERE timestamp BETWEEN ?1 AND ?2")
    Double findAverageTemperatureBetweenEndAndStartDate(LocalDateTime start, LocalDateTime end);

}
