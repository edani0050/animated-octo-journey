package com.challenge.codingchallenge;

import com.challenge.codingchallenge.controller.SensorReadingController;
import com.challenge.codingchallenge.service.SensorReadingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.challenge.codingchallenge.constant.ResponseConstant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SensorReadingControllerTest {

    public static final double TEMPERATURE = 10.3;
    public static final String SENSOR_ID = "testSensorId";

    @Mock
    private SensorReadingService sensorReadingServiceMock = mock(SensorReadingService.class);

    private SensorReadingController sensorReadingController;

    LocalDateTime endDate;
    LocalDateTime startDate;

    @BeforeEach
    public void setUp() {
        sensorReadingController = new SensorReadingController(sensorReadingServiceMock);
        startDate = LocalDate.of(2020, 1, 1).atStartOfDay();
        endDate = LocalDate.of(2021, 1, 1).atStartOfDay();
    }

    @Test
    void testGetAverageTemperatureReadingByAllSensorBetweenDatesStartDateIsAfterEndDate() {
        startDate = endDate.plusDays(1);

        ResponseEntity<String> responseEntity = sensorReadingController.getAverageTemperatureReadingByAllSensorBetweenDates(startDate, endDate);

        assertEquals(START_DATE_IS_AFTER_END_DATE, responseEntity.getBody());
        verify(sensorReadingServiceMock, times(0)).getAverageTemperature(startDate, endDate);
    }

    @Test
    void testGetAverageTemperatureReadingByAllSensorBetweenDatesNoDataFound() {
        when(sensorReadingServiceMock.getAverageTemperature(startDate, endDate)).thenReturn(null);

        ResponseEntity<String> responseEntity = sensorReadingController.getAverageTemperatureReadingByAllSensorBetweenDates(startDate, endDate);

        assertEquals(String.format(NO_DATA_FOUND_IN_BETWEEN_DATES_START_END, startDate, endDate), responseEntity.getBody());
        verify(sensorReadingServiceMock, times(1)).getAverageTemperature(startDate, endDate);
    }

    @Test
    void testGetAverageTemperatureReadingByAllSensorBetweenDatesDataFound() {
        when(sensorReadingServiceMock.getAverageTemperature(startDate, endDate)).thenReturn(TEMPERATURE);

        ResponseEntity<String> responseEntity = sensorReadingController.getAverageTemperatureReadingByAllSensorBetweenDates(startDate, endDate);

        assertEquals(String.format(THE_AVERAGE_TEMPERATURE_BETWEEN_AND_IS, startDate, endDate, TEMPERATURE), responseEntity.getBody());
        verify(sensorReadingServiceMock, times(1)).getAverageTemperature(startDate, endDate);
    }

    @Test
    void testGetAverageTemperatureReadingBySensorIdBetweenDatesStartDateIsAfterEndDate() {
        startDate = endDate.plusDays(1);

        ResponseEntity<String> responseEntity = sensorReadingController.getAverageTemperatureReadingBySensorIdBetweenDates(SENSOR_ID, startDate, endDate);

        assertEquals(START_DATE_IS_AFTER_END_DATE, responseEntity.getBody());
        verify(sensorReadingServiceMock, times(0)).getAverageTemperature(startDate, endDate);
    }

    @Test
    void testGetAverageTemperatureReadingBySensorIdBetweenDatesNoDataFound() {
        when(sensorReadingServiceMock.getAverageTemperatureBySensorId(SENSOR_ID, startDate, endDate)).thenReturn(null);

        ResponseEntity<String> responseEntity = sensorReadingController.getAverageTemperatureReadingBySensorIdBetweenDates(SENSOR_ID, startDate, endDate);

        assertEquals(String.format(NO_DATA_FOUND_IN_BETWEEN_DATES_START_END_FOR_SENSOR_ID, startDate, endDate, SENSOR_ID), responseEntity.getBody());
        verify(sensorReadingServiceMock, times(1)).getAverageTemperatureBySensorId(SENSOR_ID, startDate, endDate);
    }

    @Test
    void testGetAverageTemperatureReadingBySensorIdBetweenDatesDataFound() {
        when(sensorReadingServiceMock.getAverageTemperatureBySensorId(SENSOR_ID, startDate, endDate)).thenReturn(TEMPERATURE);

        ResponseEntity<String> responseEntity = sensorReadingController.getAverageTemperatureReadingBySensorIdBetweenDates(SENSOR_ID, startDate, endDate);

        assertEquals(String.format(THE_AVERAGE_TEMPERATURE_BETWEEN_AND_FOR_SENSOR_ID_IS, startDate, endDate, SENSOR_ID, TEMPERATURE), responseEntity.getBody());
        verify(sensorReadingServiceMock, times(1)).getAverageTemperatureBySensorId(SENSOR_ID, startDate, endDate);
    }
}
