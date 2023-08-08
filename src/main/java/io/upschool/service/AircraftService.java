package io.upschool.service;

import io.upschool.dto.request.AircraftRequest;
import io.upschool.entity.Aircraft;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.AircraftMapper;
import io.upschool.repository.AircraftRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    @Transactional
    public Aircraft save(AircraftRequest aircraftRequest) throws DuplicateEntryException {
        ServiceExceptionUtil.check(aircraftRepository::existsByIataCode, aircraftRequest.getIataCode(), () -> new DuplicateEntryException("iata code"));
        Aircraft aircraft = aircraftMapper.map(aircraftRequest);
        Optional.ofNullable(aircraft.getSeats())
                .ifPresent(seats -> seats.forEach(seat -> seat.setAircraft(aircraft)));
        return aircraftRepository.save(aircraft);
    }
}
