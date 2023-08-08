package io.upschool.mapper.response;

import io.upschool.dto.response.AirlineResponse;
import io.upschool.entity.Aircraft;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AircraftResponseMapping {
    AirlineResponse map(Aircraft aircraft);
    List<AirlineResponse> map(List<Aircraft> aircraft);
}
