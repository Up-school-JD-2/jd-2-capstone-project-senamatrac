package io.upschool.service;

import io.upschool.dto.request.create.AircraftTypeCreateRequest;
import io.upschool.dto.request.search.AircraftTypeSearchRequest;
import io.upschool.entity.AircraftType;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.exception.ServiceExceptionUtil;
import io.upschool.mapper.entity.AircraftTypeMapper;
import io.upschool.mapper.entity.SeatMapper;
import io.upschool.repository.AircraftTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AircraftTypeService {
    private final AircraftTypeRepository aircraftTypeRepository;
    private final AircraftTypeMapper aircraftTypeMapper;
    private final SeatMapper seatMapper;


    @Transactional
    public AircraftType save(AircraftTypeCreateRequest aircraftTypeCreateRequest) throws DuplicateEntryException {
        ServiceExceptionUtil.check(aircraftTypeRepository::existsByIataCode, aircraftTypeCreateRequest.getIataCode(), () -> new DuplicateEntryException("iata code"));
        AircraftType aircraftType = aircraftTypeMapper.map(aircraftTypeCreateRequest);
        Optional.ofNullable(aircraftType.getSeats())
                .ifPresent(seats -> seats.forEach(seat -> seat.setAircraftType(aircraftType)));
        return aircraftTypeRepository.save(aircraftType);
    }

    @Transactional
    public List<AircraftType> saveAll(List<AircraftTypeCreateRequest> aircraftTypeCreateRequests) {
        return aircraftTypeCreateRequests.stream().map(x -> {
            try {
                return save(x);
            } catch (DuplicateEntryException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public Page<AircraftType> findAll(Pageable pageable) {
        return aircraftTypeRepository.findAll(pageable);
    }

    public List<AircraftType> findAll() {
        return aircraftTypeRepository.findAll();
    }

    public AircraftType findById(Long id) throws DataNotFoundException {
        return aircraftTypeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("airport id:" + id));
    }

    public Page<AircraftType> search(AircraftTypeSearchRequest aircraftTypeSearchRequest, Pageable pageable) {
        return null;
    }

    //--------> UPDATE <--------\\
    public AircraftType update(Long id, AircraftTypeCreateRequest aircraftTypeCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        AircraftType aircraftType = aircraftTypeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("aircraft id: " + id));
        ServiceExceptionUtil.check(aircraftTypeRepository::existsByIataCode, aircraftType.getIataCode(), () -> new DuplicateEntryException("iata code"));

        aircraftType.setIataCode(aircraftTypeCreateRequest.getIataCode());
        aircraftType.setModel(aircraftTypeCreateRequest.getModel());
        aircraftType.setSeats(seatMapper.map(aircraftTypeCreateRequest.getSeats()));

        return aircraftTypeRepository.save(aircraftType);
    }

    //--------> DELETE <--------\\
    @Transactional
    public void delete(Long id) throws DataNotFoundException, DataCannotDelete {


    }
}
