package io.upschool.service;

import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.response.FlightResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.enums.LegType;
import io.upschool.exception.DataNotFoundException;
import io.upschool.mapper.entity.FlightMapper;
import io.upschool.mapper.entity.FlightSeatPriceMapper;
import io.upschool.mapper.response.FlightResponseMapper;
import io.upschool.repository.AirlineRepository;
import io.upschool.repository.FlightRepository;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;
    public final FlightMapper flightMapper;
    public final FlightSeatPriceMapper flightSeatPriceMapper;
    private final AirlineRepository airlineRepository;
    private final RouteRepository routeRepository;
    private final FlightResponseMapper flightResponseMapper;

    public FlightResponse save(FlightRequest flightRequest) throws DataNotFoundException {
        Airline airline = airlineRepository.findById(flightRequest.getAirlineId()).orElseThrow(() -> new DataNotFoundException("airline id:" + flightRequest.getAirlineId()));
        Route route = routeRepository.findById(flightRequest.getRouteId()).orElseThrow(() -> new DataNotFoundException("route id:" + flightRequest.getRouteId()));
        LegType legType = !Objects.equals(route.getOrigin().getCity().getCountry().getName(), route.getDestination().getCity().getCountry().getName()) ? LegType.INTERNATIONAL : LegType.DOMESTIC;

        Flight flight = flightMapper.customMap(flightRequest, airline, route, legType);
        flight.getFlightSeatPrices().forEach(flightSeatPrice -> flightSeatPrice.setFlight(flight));

       /* Set<FlightSeatPrice> flightSeatPrices = flightRequest.getFlightSeatPrices().stream()
                .map(flightSeatPriceRequest -> flightSeatPriceMapper.map(flightSeatPriceRequest,flight)).collect(Collectors.toSet());
*/
        Flight flight1 = flightRepository.save(flight);
        return flightResponseMapper.map(flight1);
    }
}
