package io.upschool.mapper.response;

import io.upschool.dto.response.AircraftTypeResponse;
import io.upschool.entity.AircraftType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AircraftTypeResponseMapping {
    AircraftTypeResponse map(AircraftType aircraftType);

    List<AircraftTypeResponse> map(List<AircraftType> aircraftType);
}
