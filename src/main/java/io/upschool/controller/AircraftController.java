package io.upschool.controller;

import io.upschool.dto.request.AircraftRequest;
import io.upschool.dto.response.AirlineResponse;
import io.upschool.entity.Aircraft;
import io.upschool.mapper.response.AircraftResponseMapping;
import io.upschool.service.AircraftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aircrafts")
@RequiredArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;
    private final AircraftResponseMapping aircraftResponseMapping ;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<AirlineResponse> create(@Valid @RequestBody AircraftRequest aircraftRequest) {
        Aircraft aircraft = aircraftService.save(aircraftRequest);
        return ResponseEntity.ok(aircraftResponseMapping.map(aircraft));
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
