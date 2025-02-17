package com.challenge.codingchallenge.repository;

import com.challenge.codingchallenge.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {

    @Query("SELECT AVG(temperature) FROM SensorReading WHERE sensorId = ?1 AND timestamp BETWEEN ?2 AND ?3")
    Double findAverageTemperatureBySensorIdBetweenEndAndStartDate(String sensorId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT AVG(temperature) FROM SensorReading WHERE timestamp BETWEEN ?1 AND ?2")
    Double findAverageTemperatureBetweenEndAndStartDate(LocalDateTime start, LocalDateTime end);

}
