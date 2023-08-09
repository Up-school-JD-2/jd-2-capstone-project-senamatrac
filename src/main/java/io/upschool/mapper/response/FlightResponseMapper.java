package io.upschool.mapper.response;

import io.upschool.dto.response.FlightResponse;
import io.upschool.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FlightSeatPriceResponseMapper.class, AirlineResponseMapper.class, RouteResponseMapper.class})
public interface FlightResponseMapper {
    FlightResponse map(Flight flight);
}
