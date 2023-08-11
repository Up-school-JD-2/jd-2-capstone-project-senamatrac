package io.upschool.service;

import io.upschool.dto.request.AircraftTypeRequest;
import io.upschool.entity.AircraftType;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.AircraftTypeMapper;
import io.upschool.repository.AircraftTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AircraftTypeService {
    private final AircraftTypeRepository aircraftTypeRepository;
    private final AircraftTypeMapper aircraftTypeMapper;

    @Transactional
    public AircraftType save(AircraftTypeRequest aircraftTypeRequest) throws DuplicateEntryException {
        ServiceExceptionUtil.check(aircraftTypeRepository::existsByIataCode, aircraftTypeRequest.getIataCode(), () -> new DuplicateEntryException("iata code"));
        AircraftType aircraftType = aircraftTypeMapper.map(aircraftTypeRequest);
        Optional.ofNullable(aircraftType.getSeats())
                .ifPresent(seats -> seats.forEach(seat -> seat.setAircraftType(aircraftType)));
        return aircraftTypeRepository.save(aircraftType);
    }
}
