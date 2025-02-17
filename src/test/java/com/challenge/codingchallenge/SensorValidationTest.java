package com.challenge.codingchallenge;

import com.challenge.codingchallenge.constant.ValidationConstants;
import com.challenge.codingchallenge.dto.SensorReadingDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class SensorValidationTest {

    public static final String TEST_ID = "testId";
    public static final double TEMPERATURE = 10.0;
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testSensorDtoValidationSensorIdIsNull() {
        SensorReadingDTO sensorReadingDTO = new SensorReadingDTO(null, 10.0, LocalDateTime.now());

        Set<ConstraintViolation<SensorReadingDTO>> violations = validator.validate(sensorReadingDTO);
        assertFalse(violations.isEmpty());
        assertEquals(ValidationConstants.SENSOR_ID_IS_MANDATORY, violations.iterator().next().getMessage());
    }

    @Test
    void testSensorDtoValidationSensorIdEmpty() {
        SensorReadingDTO sensorReadingDTO = new SensorReadingDTO("", 10.0, LocalDateTime.now());

        Set<ConstraintViolation<SensorReadingDTO>> violations = validator.validate(sensorReadingDTO);
        assertFalse(violations.isEmpty());
        assertEquals(ValidationConstants.SENSOR_ID_IS_MANDATORY, violations.iterator().next().getMessage());
    }

    @Test
    void testSensorDtoValidationTemperatureIsNull() {
        SensorReadingDTO sensorReadingDTO = new SensorReadingDTO(TEST_ID, null, LocalDateTime.now());

        Set<ConstraintViolation<SensorReadingDTO>> violations = validator.validate(sensorReadingDTO);
        assertFalse(violations.isEmpty());
        assertEquals(ValidationConstants.TEMPERATURE_IS_MANDATORY, violations.iterator().next().getMessage());
    }

    @Test
    void testSensorDtoValidation() {
        SensorReadingDTO sensorReadingDTO = new SensorReadingDTO(TEST_ID, TEMPERATURE, null);

        Set<ConstraintViolation<SensorReadingDTO>> violations = validator.validate(sensorReadingDTO);
        assertFalse(violations.isEmpty());
        assertEquals(ValidationConstants.TIMESTAMP_IS_MANDATORY, violations.iterator().next().getMessage());
    }
}

