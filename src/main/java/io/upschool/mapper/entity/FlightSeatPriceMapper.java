package io.upschool.mapper.entity;

import io.upschool.dto.request.FlightSeatPriceRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.FlightSeatPrice;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {FlightMapper.class})
public interface FlightSeatPriceMapper {
    @Mapping(target = "flight", source = "flight")
    @Mapping(target = "seatType", source = "flightSeatPriceRequest.seatType")
    @Mapping(target = "price", source = "flightSeatPriceRequest.price")
    @Mapping(target = "id", ignore = true)
    @Named("MapFlightToFSP")
    FlightSeatPrice mapFlightToFSP(FlightSeatPriceRequest flightSeatPriceRequest, Flight flight);

    /*@IterableMapping( qualifiedByName = "MapFlightToFSP")
    @Mapping(target = "id", ignore = true)
    List<FlightSeatPrice> map(List<FlightSeatPriceRequest> flightSeatPriceRequest, Flight flight);*/

    default Set<FlightSeatPrice> mapFlightToFSPList(Set<FlightSeatPrice> flightSeatPrice, Flight flight){
        flightSeatPrice.forEach(fsp->fsp.setFlight(flight));
        return flightSeatPrice;
    }


}
