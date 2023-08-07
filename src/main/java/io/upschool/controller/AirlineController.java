package io.upschool.controller;

import io.upschool.dto.request.AirlineRequest;
import io.upschool.dto.request.search.AirlineSearchRequest;
import io.upschool.dto.response.AirlineResponse;
import io.upschool.entity.Airline;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.AirlineResponseMapper;
import io.upschool.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/airlines"))
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;
    private final AirlineResponseMapper airlineResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<AirlineResponse> create(@Valid @RequestBody  AirlineRequest airlineRequest) throws DuplicateEntryException {
        Airline airline = airlineService.save(airlineRequest);
        return ResponseEntity.ok(airlineResponseMapper.map(airline));
    }

    @PostMapping("/all")
    public ResponseEntity<List<AirlineResponse>> createAll(@RequestBody List<AirlineRequest> airlineRequests) {
        List<Airline> airlines = airlineService.saveAll(airlineRequests);
        return ResponseEntity.ok(airlineResponseMapper.map(airlines));
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getPageable(Pageable pageable) {
        Page<Airline> airlines = airlineService.getAll(pageable);
        return ResponseEntity.ok(airlines.map(airlineResponseMapper::map));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirlineResponse>> getAll() {
        return ResponseEntity.ok(airlineResponseMapper.map(airlineService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getById(@PathVariable Long id) throws DataNotFoundException {
        return  ResponseEntity.ok(airlineResponseMapper.map(airlineService.getById(id)));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<AirlineResponse>> search(@RequestBody AirlineSearchRequest airlineSearchRequest, Pageable pageable) {
        Page<Airline> airlines = airlineService.search(airlineSearchRequest,pageable);
        return ResponseEntity.ok(airlines.map(airlineResponseMapper::map));
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public ResponseEntity<AirlineResponse> update(@PathVariable Long id, @Valid @RequestBody AirlineRequest airlineRequest) throws DataNotFoundException, DuplicateEntryException {
        Airline airline = airlineService.update(id,airlineRequest);
        return ResponseEntity.ok(airlineResponseMapper.map(airline));
    }

    //--------> DELETE <--------\\
    @DeleteMapping(("/{id}"))
    public void save() {
        //Airporta bağlı rota varsa?
    }
}
