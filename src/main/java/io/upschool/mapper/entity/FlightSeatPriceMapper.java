package io.upschool.mapper.entity;

import io.upschool.dto.request.create.FlightSeatPriceCreateRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.FlightSeatPrice;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {FlightMapper.class}, builder = @Builder(disableBuilder = true))
public interface FlightSeatPriceMapper {
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "seatType", source = "flightSeatPriceCreateRequest.seatType")
    @Mapping(target = "price", source = "flightSeatPriceCreateRequest.price")
    @Named("MapFlightToFSP")
    FlightSeatPrice mapFlightToFSPRequest(FlightSeatPriceCreateRequest flightSeatPriceCreateRequest, Flight flight);


    default Set<FlightSeatPrice> mapFlightToFSPList(Set<FlightSeatPrice> flightSeatPrice, Flight flight) {
        flightSeatPrice.forEach(fsp -> fsp.setFlight(flight));
        return flightSeatPrice;
    }


}
