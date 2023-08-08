package io.upschool.mapper.entity;

import io.upschool.dto.request.AircraftRequest;
import io.upschool.entity.Aircraft;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SeatMapper.class})
public interface AircraftMapper {
    Aircraft map(AircraftRequest aircraftRequest);
}
