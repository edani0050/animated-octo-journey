package com.challenge.codingchallenge.mapper;

import com.challenge.codingchallenge.dto.SensorReadingDTO;
import com.challenge.codingchallenge.entity.SensorReading;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SensorReadingMapper {

    SensorReadingMapper INSTANCE = Mappers.getMapper(SensorReadingMapper.class);

    SensorReading mapSensorReadingDTOToSenorReading(SensorReadingDTO sensorReadingDTO);
}
