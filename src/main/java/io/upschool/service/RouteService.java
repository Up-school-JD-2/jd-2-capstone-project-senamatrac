package io.upschool.service;

import io.upschool.dto.request.create.RouteCreateRequest;
import io.upschool.dto.request.search.RouteSearchRequest;
import io.upschool.entity.Airport;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.enums.RouteStatus;
import io.upschool.exception.CannotChangeStatus;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.RouteMapper;
import io.upschool.repository.AirportRepository;
import io.upschool.repository.FlightRepository;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final AirportRepository airportRepository;
    private final RouteMapper routeMapper;
    private final FlightRepository flightRepository;

    //--------> CREATE <--------\\
    public Route save(RouteCreateRequest routeCreateRequest) throws DuplicateEntryException, DataNotFoundException {
        ServiceExceptionUtil.check(() -> routeRepository.existsByOrigin_IdAndDestination_Id(routeCreateRequest.getOriginAirportId(), routeCreateRequest.getDestinationAirportId()), () -> new DuplicateEntryException("origin-destination: " + routeCreateRequest.getOriginAirportId() + "-" + routeCreateRequest.getDestinationAirportId()));
        Airport origin = airportRepository.findById(routeCreateRequest.getOriginAirportId()).orElseThrow(() -> new DataNotFoundException("origin airport id:" + routeCreateRequest.getOriginAirportId()));
        Airport destination = airportRepository.findById(routeCreateRequest.getDestinationAirportId()).orElseThrow(() -> new DataNotFoundException("destination airport id:" + routeCreateRequest.getDestinationAirportId()));

        Route r = Route.builder().origin(origin).destination(destination).duration(routeCreateRequest.getDuration()).status(RouteStatus.ACTIVE).build();
        return routeRepository.save(r);
    }

    public List<Route> saveAll(List<RouteCreateRequest> routeCreateRequests) throws DuplicateEntryException, DataNotFoundException {
        List<Route> list = new ArrayList<>();
        for (RouteCreateRequest routeCreateRequest : routeCreateRequests) {
            Route savedRoute = save(routeCreateRequest);
            list.add(savedRoute);
        }
        return list;
    }

    //--------> READ <--------\\
    public Page<Route> findAll(Pageable pageable) {
        return routeRepository.findAll(pageable);
    }

    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    public Route findById(Long id) throws DataNotFoundException {
        Route route = routeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("route id:" + id));
        return route;
    }

    public Page<Route> search(RouteSearchRequest routeSearchRequest, Pageable pageable) {
        Route route = routeMapper.map(routeSearchRequest);
        Example<Route> search = Example.of(route, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return routeRepository.findAll(search, pageable);
    }

    public Route cancel(Long id) throws DataNotFoundException {
        Route route = routeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("route id:" + id));
        List<Flight> flights = flightRepository.findByRoute_Id(id);

        if (!flights.isEmpty()) {
            throw new CannotChangeStatus("The route cannot cancel cause it has some flights active on it");
        }

        route.setStatus(RouteStatus.CANCELED);

        return routeRepository.save(route);
    }
}
