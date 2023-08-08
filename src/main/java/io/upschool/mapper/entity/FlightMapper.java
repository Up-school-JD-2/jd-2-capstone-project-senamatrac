package io.upschool.mapper.entity;

import io.upschool.dto.request.FlightRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.FlightSeatPrice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {FlightSeatPriceMapper.class})
public interface FlightMapper {
    Flight map(FlightRequest flightRequest);
}
