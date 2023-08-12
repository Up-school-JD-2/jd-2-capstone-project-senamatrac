package io.upschool.mapper.entity;

import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.request.search.FlightSearchRequest;
import io.upschool.entity.AircraftType;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.enums.LegType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {FlightSeatPriceMapper.class},builder = @Builder(disableBuilder = true))
public abstract class FlightMapper {
    @Mapping(target = "legType",source = "legType")
    @Mapping(target = "route",source = "route")
    @Mapping(target = "airline",source = "airline")
    @Mapping(target = "aircraftType",source = "aircraftType")
    @Mapping(target = ".",source = "flightRequest")
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "id",ignore = true)
    public abstract Flight map(FlightRequest flightRequest, Airline airline, AircraftType aircraftType, Route route, LegType legType);

    @AfterMapping
    protected void setFlightFSP(@MappingTarget Flight flight){
        flight.getFlightSeatPrices().forEach(f->f.setFlight(flight));
    }
    @Mapping(target = "legType",source = "legType")
    @Mapping(target = "route",source = "route")
    @Mapping(target = "airline",source = "airline")
    @Mapping(target = "aircraftType",source = "aircraftType")
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "flightSeatPrices",ignore = true)
    public abstract Flight map(FlightSearchRequest flightSearchRequest);




}
