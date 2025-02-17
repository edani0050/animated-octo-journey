package com.challenge.codingchallenge.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ResponseConstant {

    public static final String SENSOR_READING_SAVED_SUCCESSFULLY_FOR_SENSOR_ID_WITH_TEMPERATURE = "Sensor Reading saved successfully for sensor id: %s with temperature: %f";
    public static final String THE_AVERAGE_TEMPERATURE_BETWEEN_AND_IS = "The average temperature between %s and %s is: %f";
    public static final String THE_AVERAGE_TEMPERATURE_BETWEEN_AND_FOR_SENSOR_ID_IS = "The average temperature between %s and %s for sensor id: %s is: %f";
    public static final String INVALID_DATA_FORMAT = "Invalid data format: Numbers shouldn't contain characters, dates should follow ISO 8601 format (example: 2021-08-20T10:00:00Z)";
    public static final String NO_DATA_FOUND_IN_BETWEEN_DATES_START_END = "No data found in between dates start: %s, end: %s";
    public static final String NO_DATA_FOUND_IN_BETWEEN_DATES_START_END_FOR_SENSOR_ID = "No data found in between dates start: %s, end: %s for sensor id: %s";
    public static final String START_DATE_IS_AFTER_END_DATE = "Start date is after end date";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
}
