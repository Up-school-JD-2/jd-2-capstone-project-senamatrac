package io.upschool.mapper.entity;

import io.upschool.dto.request.FlightSeatPriceRequest;
import io.upschool.entity.FlightSeatPrice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightSeatPriceMapper {
    FlightSeatPrice map(FlightSeatPriceRequest flightSeatPriceRequest);
    List<FlightSeatPrice> map(List<FlightSeatPriceRequest> flightSeatPriceRequest);
}
