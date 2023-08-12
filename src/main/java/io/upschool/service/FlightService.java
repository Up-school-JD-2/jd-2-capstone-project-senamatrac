package io.upschool.service;

import io.upschool.dto.request.FlightRequest;
import io.upschool.dto.request.search.FlightSearchRequest;
import io.upschool.entity.*;
import io.upschool.enums.FlightStatus;
import io.upschool.enums.LegType;
import io.upschool.enums.PaymentStatus;
import io.upschool.enums.TicketStatus;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.FlightMapper;
import io.upschool.mapper.entity.FlightSeatPriceMapper;
import io.upschool.mapper.response.FlightResponseMapper;
import io.upschool.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    private final FlightSeatPriceRepository flightSeatPriceRepository;
    private final TicketRepository ticketRepository;

    //--------> CREATE <--------\\
    public Flight save(FlightRequest flightRequest) throws DataNotFoundException, DuplicateEntryException {
        ServiceExceptionUtil.check(flightRepository::existsByFlightNumber, flightRequest.getFlightNumber(), () -> new DuplicateEntryException("flight number : " + flightRequest.getFlightNumber()));

        Airline airline = airlineRepository.findById(flightRequest.getAirlineId()).orElseThrow(() -> new DataNotFoundException("airline id:" + flightRequest.getAirlineId()));
        Route route = routeRepository.findById(flightRequest.getRouteId()).orElseThrow(() -> new DataNotFoundException("route id:" + flightRequest.getRouteId()));
        AircraftType aircraftType = aircraftTypeRepository.findById(flightRequest.getAircraftTypeId()).orElseThrow(() -> new DataNotFoundException("aircraft type id:" + flightRequest.getAircraftTypeId()));

        LegType legType = !Objects.equals(route.getOrigin().getCity().getCountry().getName(), route.getDestination().getCity().getCountry().getName()) ? LegType.INTERNATIONAL : LegType.DOMESTIC;

        Flight flight = flightMapper.map(flightRequest, airline, aircraftType, route, legType);

        return flightRepository.save(flight);
    }

    public List<Flight> saveAll(List<FlightRequest> flightRequests) {
        return flightRequests.stream().map(flightRequest ->
                {
                    try {
                        return save(flightRequest);
                    } catch (DataNotFoundException | DuplicateEntryException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).toList();
    }

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

        return flightRepository.findAll(example,pageable);
    }

    @Transactional
    public Flight cancel(Long id) throws DuplicateEntryException, DataNotFoundException {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new DataNotFoundException("city id: " + id));
        flight.setStatus(FlightStatus.CANCELED);

        List<Ticket> tickets = ticketRepository.findByFlight_Id(id);
        if(!tickets.isEmpty()) {
            tickets.forEach(ticket -> {
                ticket.setStatus(TicketStatus.CANCELED);
                ticket.getPayment().setStatus(PaymentStatus.REFUNDED);
                ticketRepository.save(ticket);
            });
        }

        return flightRepository.save(flight);
    }
}
