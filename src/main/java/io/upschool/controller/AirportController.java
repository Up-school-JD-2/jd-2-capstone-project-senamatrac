package io.upschool.controller;

import io.upschool.dto.request.AirportRequest;
import io.upschool.dto.request.search.AirportSearchRequest;
import io.upschool.dto.response.AirportResponse;
import io.upschool.entity.Airport;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AirportResponseMapper;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    private final AirportResponseMapper airportResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<AirportResponse> create(@Valid @RequestBody AirportRequest airportRequest) throws DataNotFoundException, DuplicateEntryException {
        Airport airport = airportService.save(airportRequest);
        return ResponseEntity.ok(airportResponseMapper.map(airport));
    }

    @PostMapping("/all")
    public List<AirportResponse> createAll(@RequestBody List<AirportRequest> airportRequests) {
        return airportResponseMapper.map(airportService.saveAll(airportRequests));
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<Page<AirportResponse>> getPageable(Pageable pageable) {
        Page<Airport> airports = airportService.findAll(pageable);
        return ResponseEntity.ok(airports.map(airportResponseMapper::map));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirportResponse>> getAll() {
        List<Airport> airports = airportService.findAll();
        return ResponseEntity.ok(airportResponseMapper.map(airports));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        Airport airport = airportService.findById(id);
        return ResponseEntity.ok(airportResponseMapper.map(airport));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AirportResponse>> search(@RequestBody AirportSearchRequest airportSearchRequest, Pageable pageable) {
        Page<Airport> airports = airportService.search(airportSearchRequest, pageable);

        return ResponseEntity.ok(airports.map(airportResponseMapper::map));
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> update(@PathVariable Long id, @Valid @RequestBody AirportRequest airportRequest) throws DataNotFoundException, DuplicateEntryException {
        Airport airport = airportService.update(id, airportRequest);
        return ResponseEntity.ok(airportResponseMapper.map(airport));
    }

    //--------> DELETE <--------\\
    @DeleteMapping(("/{id}"))
    public void save(@PathVariable Long id) {
//Airporta bağlı rota varsa?
    }


}
