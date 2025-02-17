package com.challenge.codingchallenge.constant;

@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoggingConstants {

    //SensorReadingController
    public static final String SENSOR_READING_CONTROLLER_SAVE_SENSOR_READING = "SensorReadingController -> saveSensorReading";
    public static final String SENSOR_READING_CONTROLLER_GET_AVERAGE_TEMPERATURE_READING_BY_ALL_SENSOR_BETWEEN_DATES = "SensorReadingController -> getAverageTemperatureReadingByAllSensorBetweenDates";
    public static final String SENSOR_READING_CONTROLLER_GET_AVERAGE_TEMPERATURE_READING_BY_SENSOR_ID_BETWEEN_DATES = "SensorReadingController -> getAverageTemperatureReadingBySensorIdBetweenDates";
    public static final String NO_DATA_FOUND_FOR_REQUESTED_TIME_PERIOD = "SensorReadingController -> No data found for requested time period";
    public static final String SAVE_SUCCESSFUL = "Save Successful";

    //SensorReadingService
    public static final String SENSOR_READING_SERVICE_GETTING_AVERAGE_TEMPERATURE_BETWEEN_AND = "SensorReadingService -> Getting average temperature between {} and {}";
    public static final String SENSOR_READING_SERVICE_GETTING_AVERAGE_TEMPERATURE_FOR_SENSOR_ID_BETWEEN_AND = "SensorReadingService -> Getting average temperature for sensorId {} between {} and {}";
    public static final String SENSOR_READING_SERVICE_SAVING_SENSOR_READING = "SensorReadingService -> Saving sensor reading {}";
}
