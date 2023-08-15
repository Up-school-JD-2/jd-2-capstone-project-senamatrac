package io.upschool.controller;

import io.upschool.dto.request.create.AircraftTypeCreateRequest;
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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<AircraftTypeResponse>> create(@Valid @RequestBody AircraftTypeCreateRequest aircraftTypeCreateRequest) throws DuplicateEntryException {
        AircraftType aircraftType = aircraftTypeService.save(aircraftTypeCreateRequest);
        var response = BaseResponse.<AircraftTypeResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftType))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<AircraftTypeResponse>>> createAll(@Valid @RequestBody List<AircraftTypeCreateRequest> aircraftTypeCreateRequests) throws DuplicateEntryException {
        List<AircraftType> aircraftTypes = aircraftTypeService.saveAll(aircraftTypeCreateRequests);
        var response = BaseResponse.<List<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftTypes))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<AircraftTypeResponse>>> getAllAircraftTypes(Pageable pageable) {
        Page<AircraftType> aircraftTypePage = aircraftTypeService.findAll(pageable);
        var response = BaseResponse.<Page<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypePage.map(aircraftTypeResponseMapping::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<AircraftTypeResponse>>> getAllAircraftTypes() {
        List<AircraftType> aircraftTypeList = aircraftTypeService.findAll();
        var response = BaseResponse.<List<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftTypeList))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<AircraftTypeResponse>> getById(@PathVariable Long id) throws DataNotFoundException {
        AircraftType aircraftType = aircraftTypeService.findById(id);
        var response = BaseResponse.<AircraftTypeResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftType))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<AircraftTypeResponse>>> search(Pageable pageable, @Valid @RequestBody AircraftTypeSearchRequest aircraftTypeSearchRequest) {
        Page<AircraftType> aircraftTypePage = aircraftTypeService.search(aircraftTypeSearchRequest, pageable);
        var response = BaseResponse.<Page<AircraftTypeResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(aircraftTypePage.map(aircraftTypeResponseMapping::map))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<AircraftTypeResponse>> update(@PathVariable Long id, @Valid @RequestBody AircraftTypeCreateRequest aircraftTypeCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        AircraftType aircraftType = aircraftTypeService.update(id, aircraftTypeCreateRequest);
        var response = BaseResponse.<AircraftTypeResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(aircraftTypeResponseMapping.map(aircraftType))
                .build();
        return ResponseEntity.ok(response);
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
