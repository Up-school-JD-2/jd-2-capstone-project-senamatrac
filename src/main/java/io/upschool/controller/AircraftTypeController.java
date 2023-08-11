package io.upschool.controller;

import io.upschool.dto.request.AircraftTypeRequest;
import io.upschool.dto.response.AirlineResponse;
import io.upschool.entity.AircraftType;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AircraftResponseMapping;
import io.upschool.service.AircraftTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aircraft")
@RequiredArgsConstructor
public class AircraftTypeController {
    private final AircraftTypeService aircraftTypeService;
    private final AircraftResponseMapping aircraftResponseMapping;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<AirlineResponse> create(@Valid @RequestBody AircraftTypeRequest aircraftTypeRequest) throws DuplicateEntryException {
        AircraftType aircraftType = aircraftTypeService.save(aircraftTypeRequest);
        return ResponseEntity.ok(aircraftResponseMapping.map(aircraftType));
    }

    @PostMapping("/all")
    public void createAll() {

    }

    //--------> READ <--------\\
    @GetMapping
    public void getPageable(Pageable pageable) {

    }

    @GetMapping("/all")
    public void getAll() {
    }

    @GetMapping("/{id}")
    public void getById(@PathVariable Long id) {

    }

    @GetMapping("/search")
    public void search() {
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public void update() {

    }

    //--------> DELETE <--------\\
    @DeleteMapping(("/{id}"))
    public void save() {
        //Airporta bağlı rota varsa?
    }
}
