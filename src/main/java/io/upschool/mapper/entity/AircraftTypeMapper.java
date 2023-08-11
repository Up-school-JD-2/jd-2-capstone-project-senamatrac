package io.upschool.mapper.entity;

import io.upschool.dto.request.AircraftTypeRequest;
import io.upschool.entity.AircraftType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SeatMapper.class})
public interface AircraftTypeMapper {
    AircraftType map(AircraftTypeRequest aircraftTypeRequest);
}
