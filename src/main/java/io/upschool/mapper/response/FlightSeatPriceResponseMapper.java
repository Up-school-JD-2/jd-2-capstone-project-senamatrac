package io.upschool.mapper.response;

import io.upschool.dto.response.FlightSeatPriceResponse;
import io.upschool.entity.FlightSeatPrice;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface FlightSeatPriceResponseMapper {
    FlightSeatPriceResponse map(FlightSeatPrice flightSeatPrice);

    Set<FlightSeatPriceResponse> map(Set<FlightSeatPrice> flightSeatPrices);
}
