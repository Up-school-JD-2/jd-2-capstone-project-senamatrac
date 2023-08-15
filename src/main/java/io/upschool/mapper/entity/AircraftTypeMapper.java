package io.upschool.mapper.entity;

import io.upschool.dto.request.create.AircraftTypeCreateRequest;
import io.upschool.entity.AircraftType;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SeatMapper.class}, builder = @Builder(disableBuilder = true))
public interface AircraftTypeMapper {
    AircraftType map(AircraftTypeCreateRequest aircraftTypeCreateRequest);
}
