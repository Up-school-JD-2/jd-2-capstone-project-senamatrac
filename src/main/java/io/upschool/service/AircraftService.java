package io.upschool.service;

import io.upschool.dto.request.AircraftRequest;
import io.upschool.entity.Aircraft;
import io.upschool.mapper.entity.AircraftMapper;
import io.upschool.repository.AircraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;

    public Aircraft save(AircraftRequest aircraftRequest) {
        Aircraft aircraft = aircraftMapper.map(aircraftRequest);
        return  aircraftRepository.save(aircraft);
    }
}
