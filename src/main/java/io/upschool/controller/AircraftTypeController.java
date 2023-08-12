package io.upschool.controller;

import io.upschool.dto.request.AircraftTypeRequest;
import io.upschool.dto.request.search.AircraftTypeSearchRequest;
import io.upschool.dto.response.AircraftTypeResponse;
import io.upschool.dto.response.BaseResponse;
import io.upschool.entity.AircraftType;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AircraftTypeResponseMapping;
import io.upschool.service.AircraftTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft_type")
@RequiredArgsConstructor
public class AircraftTypeController {
    private final AircraftTypeService aircraftTypeService;
    private final AircraftTypeResponseMapping aircraftTypeResponseMapping;

    //--------> CREATE <--------\\
    @PostMapping
    public BaseResponse<AircraftTypeResponse> create(@Valid @RequestBody AircraftTypeRequest aircraftTypeRequest) throws DuplicateEntryException {
        AircraftType aircraftType = aircraftTypeService.save(aircraftTypeRequest);
        return BaseResponse.<AircraftTypeResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftType))
                .build();
    }

    @PostMapping("/all")
    public BaseResponse<List<AircraftTypeResponse>>  createAll(@Valid @RequestBody List<AircraftTypeRequest> aircraftTypeRequests) throws DuplicateEntryException{
        List<AircraftType> aircraftTypes = aircraftTypeService.saveAll(aircraftTypeRequests);
        return  BaseResponse.<List<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftTypes))
                .build();
    }

    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<AircraftTypeResponse>> getAllAircraftTypes(Pageable pageable) {
        Page<AircraftType> aircraftTypePage = aircraftTypeService.findAll(pageable);
        return BaseResponse.<Page<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypePage.map(aircraftTypeResponseMapping::map))
                .build();
    }

    @GetMapping("/all")
    public BaseResponse<List<AircraftTypeResponse>> getAllAircraftTypes() {
        List<AircraftType> aircraftTypeList = aircraftTypeService.findAll();
        return BaseResponse.<List<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftTypeList))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<AircraftTypeResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        AircraftType aircraftType = aircraftTypeService.findById(id);
        return BaseResponse.<AircraftTypeResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftType))
                .build();
    }

    @GetMapping("/search")
    public BaseResponse<Page<AircraftTypeResponse>> search(Pageable pageable, @Valid @RequestBody AircraftTypeSearchRequest aircraftTypeSearchRequest) {
        Page<AircraftType> aircraftTypePage = aircraftTypeService.search(aircraftTypeSearchRequest, pageable);
        return BaseResponse.<Page<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypePage.map(aircraftTypeResponseMapping::map))
                .build();
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public BaseResponse<AircraftTypeResponse> update(@PathVariable Long id, @Valid @RequestBody AircraftTypeRequest aircraftTypeRequest) throws DataNotFoundException, DuplicateEntryException {
        AircraftType aircraftType = aircraftTypeService.update(id, aircraftTypeRequest);
        return BaseResponse.<AircraftTypeResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftType))
                .build();
    }

    //--------> DELETE <--------\\
    @DeleteMapping({"/{id}"})
    public BaseResponse<String> delete(@PathVariable Long id) throws DataNotFoundException, DataCannotDelete {
        return BaseResponse.<String>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody("soon")
                .build();
    }
}
