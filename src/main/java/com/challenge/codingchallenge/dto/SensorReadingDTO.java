package com.challenge.codingchallenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static com.challenge.codingchallenge.constant.ValidationConstants.*;

public record SensorReadingDTO(
        @NotBlank(message = SENSOR_ID_IS_MANDATORY)
        String sensorId,
        @NotNull(message = TEMPERATURE_IS_MANDATORY)
        Double temperature,
        @NotNull(message = TIMESTAMP_IS_MANDATORY)
        LocalDateTime timestamp) {

}
