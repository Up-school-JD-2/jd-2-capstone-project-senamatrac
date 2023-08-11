package io.upschool.mapper.response;

import io.upschool.dto.response.AirlineResponse;
import io.upschool.entity.AircraftType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AircraftResponseMapping {
    AirlineResponse map(AircraftType aircraftType);

    List<AirlineResponse> map(List<AircraftType> aircraftType);
}
