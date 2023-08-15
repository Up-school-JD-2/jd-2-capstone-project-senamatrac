package io.upschool.mapper.response;

import io.upschool.dto.response.FlightResponse;
import io.upschool.entity.Flight;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FlightSeatPriceResponseMapper.class, AirlineResponseMapper.class, RouteResponseMapper.class})
public interface FlightResponseMapper {
    FlightResponse map(Flight flight);

    List<FlightResponse> map(List<Flight> flight);
}
