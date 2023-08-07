package io.upschool.mapper.response;

import io.upschool.dto.response.AirlineResponse;
import io.upschool.entity.Airline;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirlineResponseMapper {
    AirlineResponse map(Airline airline);

    List<AirlineResponse> map(List<Airline> airlines);
}
