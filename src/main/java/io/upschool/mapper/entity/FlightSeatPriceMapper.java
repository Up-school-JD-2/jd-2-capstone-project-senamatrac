package io.upschool.mapper.entity;

import io.upschool.dto.request.FlightSeatPriceRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.FlightSeatPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {FlightMapper.class})
public interface FlightSeatPriceMapper {
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "price", source = "flightSeatPriceRequest.price")
    @Mapping(target = "seatType", source = "flightSeatPriceRequest.seatType")
    @Mapping(target = "id", ignore = true)
    FlightSeatPrice map(FlightSeatPriceRequest flightSeatPriceRequest, Flight flight);

    List<FlightSeatPrice> map(List<FlightSeatPriceRequest> flightSeatPriceRequest);

    Set<FlightSeatPrice> map(Set<FlightSeatPriceRequest> flightSeatPriceRequest);
}
