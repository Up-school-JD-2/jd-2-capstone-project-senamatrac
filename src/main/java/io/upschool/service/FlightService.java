package io.upschool.service;

import io.upschool.dto.request.create.FlightCreateRequest;
import io.upschool.dto.request.search.FlightSearchRequest;
import io.upschool.entity.*;
import io.upschool.enums.*;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.FlightMapper;
import io.upschool.mapper.entity.FlightSeatPriceMapper;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlightService {
    public final FlightRepository flightRepository;
    public final FlightMapper flightMapper;
    public final FlightSeatPriceMapper flightSeatPriceMapper;
    private final AirlineService airlineService;
    private final RouteService routeService;
    private final AircraftTypeService aircraftTypeService;
    private final TicketService ticketService;

    //--------> CREATE <--------\\
    @Transactional
    public Flight save(FlightCreateRequest flightCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        ServiceExceptionUtil.check(flightRepository::existsByFlightNumber, flightCreateRequest.getFlightNumber(), () -> new DuplicateEntryException("flight number : " + flightCreateRequest.getFlightNumber()));

        Airline airline = airlineService.findById(flightCreateRequest.getAirlineId());
        Route route = routeService.findByIdAndStatus(flightCreateRequest.getRouteId(), RouteStatus.ACTIVE);
        AircraftType aircraftType = aircraftTypeService.findById(flightCreateRequest.getAircraftTypeId());

        LegType legType = !Objects.equals(route.getOrigin().getCity().getCountry().getName(), route.getDestination().getCity().getCountry().getName()) ? LegType.INTERNATIONAL : LegType.DOMESTIC;

        Flight flight = flightMapper.map(flightCreateRequest, airline, aircraftType, route, legType);
        flight.setFlightType(FlightType.DIRECT_FLIGHT);
        flight.setStatus(FlightStatus.ON_TIME);

        return flightRepository.save(flight);
    }

    @Transactional
    public List<Flight> saveAll(List<FlightCreateRequest> flightCreateRequests) {
        return flightCreateRequests.stream().map(flightCreateRequest ->
                {
                    try {
                        return save(flightCreateRequest);
                    } catch (DataNotFoundException | DuplicateEntryException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();
    }

    //--------> READ <--------\\
    public Page<Flight> findAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Flight findById(Long id) throws DataNotFoundException {
        return flightRepository.findById(id).orElseThrow(() -> new DataNotFoundException("flight id:" + id));
    }

    public Page<Flight> search(FlightSearchRequest flightSearchRequest, Pageable pageable) {
        Flight flight = flightMapper.map(flightSearchRequest);
        Example<Flight> example = Example.of(flight, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return flightRepository.findAll(example, pageable);
    }

    //--------> UPDATE <--------\\
    @Transactional
    public Flight cancel(Long id) throws DataNotFoundException {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new DataNotFoundException("city id: " + id));
        flight.setStatus(FlightStatus.CANCELED);

        List<Ticket> tickets = ticketService.findByFlight_IdAndStatusNot(id, TicketStatus.CANCELED);
        if (!tickets.isEmpty()) {
            tickets.forEach(ticket -> {
                ticket.setStatus(TicketStatus.CANCELED);
                ticket.getPayment().setStatus(PaymentStatus.REFUNDED);
                ticketService.buy(ticket);
            });
        }

        return flightRepository.save(flight);
    }

    public List<Flight> findByRoute_Id(Long id) {
        return flightRepository.findByRoute_Id(id);
    }
}
