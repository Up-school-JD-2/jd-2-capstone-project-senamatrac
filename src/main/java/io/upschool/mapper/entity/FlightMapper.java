package io.upschool.mapper.entity;

import io.upschool.dto.request.FlightRequest;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.enums.LegType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {FlightSeatPriceMapper.class})
public interface FlightMapper {
    FlightSeatPriceMapper INSTANCE = Mappers.getMapper(FlightSeatPriceMapper.class);

    default Flight customMap(FlightRequest flightRequest, Airline airline, Route route, LegType legType) {
        return Flight.builder()
                .flightNumber(flightRequest.getFlightNumber())
                .flightDate(flightRequest.getFlightDate())
                .airline(airline)
                .route(route)
                .legType(legType)
                .flightSeatPrices(INSTANCE.map(flightRequest.getFlightSeatPrices()))
                .build();
    }


}
