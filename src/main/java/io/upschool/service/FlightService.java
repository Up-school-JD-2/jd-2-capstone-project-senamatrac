package io.upschool.service;

import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.response.FlightResponse;
import io.upschool.entity.*;
import io.upschool.enums.LegType;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.FlightMapper;
import io.upschool.mapper.entity.FlightSeatPriceMapper;
import io.upschool.mapper.response.FlightResponseMapper;
import io.upschool.mapper.response.FlightSeatPriceResponseMapper;
import io.upschool.repository.AircraftTypeRepository;
import io.upschool.repository.AirlineRepository;
import io.upschool.repository.FlightRepository;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;
    public final FlightMapper flightMapper;
    public final FlightSeatPriceMapper flightSeatPriceMapper;
    private final AirlineRepository airlineRepository;
    private final RouteRepository routeRepository;
    private final FlightResponseMapper flightResponseMapper;
    private final AircraftTypeRepository aircraftTypeRepository;

    public FlightResponse save(FlightRequest flightRequest) throws DataNotFoundException, DuplicateEntryException {
        ServiceExceptionUtil.check(flightRepository::existsByFlightNumber,flightRequest.getFlightNumber(),()->new DuplicateEntryException("flight number : "+flightRequest.getFlightNumber()));
        Airline airline = airlineRepository.findById(flightRequest.getAirlineId()).orElseThrow(() -> new DataNotFoundException("airline id:" + flightRequest.getAirlineId()));
        Route route = routeRepository.findById(flightRequest.getRouteId()).orElseThrow(() -> new DataNotFoundException("route id:" + flightRequest.getRouteId()));
        AircraftType aircraftType = aircraftTypeRepository.findById(flightRequest.getAircraftTypeId()).orElseThrow(() -> new DataNotFoundException("aircraft type id:" + flightRequest.getAircraftTypeId()));
        LegType legType = !Objects.equals(route.getOrigin().getCity().getCountry().getName(), route.getDestination().getCity().getCountry().getName()) ? LegType.INTERNATIONAL : LegType.DOMESTIC;

        Flight flight = flightMapper.map(flightRequest, airline, aircraftType,route, legType);

        return flightResponseMapper.map( flightRepository.save(flight));
    }
}
