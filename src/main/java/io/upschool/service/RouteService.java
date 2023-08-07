package io.upschool.service;

import io.upschool.dto.request.RouteRequest;
import io.upschool.dto.request.search.RouteSearchRequest;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.RouteMapper;
import io.upschool.repository.AirportRepository;
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

    //--------> CREATE <--------\\
    public Route save(RouteRequest routeRequest) throws DuplicateEntryException, DataNotFoundException {
        ServiceExceptionUtil.check(()->routeRepository.existsByOrigin_IdAndDestination_Id(routeRequest.getOriginAirportId(),routeRequest.getDestinationAirportId()),()->new DuplicateEntryException("origin-destination: " + routeRequest.getOriginAirportId()+"-"+routeRequest.getDestinationAirportId()));
        Airport origin = airportRepository.findById(routeRequest.getOriginAirportId()).orElseThrow(()->new DataNotFoundException("origin airport id:" +routeRequest.getOriginAirportId()));
        Airport destination = airportRepository.findById(routeRequest.getDestinationAirportId()).orElseThrow(()->new DataNotFoundException("destination airport id:"+routeRequest.getDestinationAirportId()));

        Route r = Route.builder().origin(origin).destination(destination).duration(routeRequest.getDuration()).build();
        return routeRepository.save(r);
    }

    public List<Route> saveAll(List<RouteRequest> routeRequests)  throws DuplicateEntryException, DataNotFoundException {
        List<Route> list = new ArrayList<>();
        for (RouteRequest routeRequest : routeRequests) {
            Route savedRoute = save(routeRequest);
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
        Route route = routeRepository.findById(id).orElseThrow(()->new DataNotFoundException("route id:" +id));
        return route;
    }

    public Page<Route> search(RouteSearchRequest routeSearchRequest, Pageable pageable) {
        Route route = routeMapper.map(routeSearchRequest);
        Example<Route> search = Example.of(route, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return routeRepository.findAll(search,pageable);
    }

    public Route update(Long id, RouteRequest routeRequest) throws DataNotFoundException, DuplicateEntryException {
        ServiceExceptionUtil.check(()->routeRepository.existsByOrigin_IdAndDestination_Id(routeRequest.getOriginAirportId(),routeRequest.getDestinationAirportId()),
                ()->new DuplicateEntryException("origin-destination: " + routeRequest.getOriginAirportId()+"-"+routeRequest.getDestinationAirportId()));

        Route route = routeRepository.findById(id).orElseThrow(()->new DataNotFoundException("route id:"+id));
        Airport airportOrigin = airportRepository.findById(routeRequest.getOriginAirportId()).orElseThrow(()->new DataNotFoundException("oridin airport id:"+routeRequest.getOriginAirportId()));
        Airport airportDestination = airportRepository.findById(routeRequest.getOriginAirportId()).orElseThrow(()->new DataNotFoundException("oridin airport id:"+routeRequest.getOriginAirportId()));

        route.setOrigin(airportOrigin);
        route.setOrigin(airportDestination);
        route.setDuration(routeRequest.getDuration());
        return route;
    }
}
