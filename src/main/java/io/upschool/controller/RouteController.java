package io.upschool.controller;

import io.upschool.dto.request.RouteRequest;
import io.upschool.dto.request.search.RouteSearchRequest;
import io.upschool.dto.response.RouteResponse;
import io.upschool.entity.Route;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.RouteResponseMapper;
import io.upschool.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    private final RouteResponseMapper routeResponseMapper;

    //--------> CREATE <--------\\
    @PostMapping
    public ResponseEntity<RouteResponse> create(@Valid @RequestBody RouteRequest routeRequest) throws DataNotFoundException, DuplicateEntryException {
        Route route = routeService.save(routeRequest);
        return ResponseEntity.ok(routeResponseMapper.map(route));
    }

    @PostMapping("/all")
    public ResponseEntity<List<RouteResponse>> create(@Valid @RequestBody List<RouteRequest> routeRequests) throws DataNotFoundException, DuplicateEntryException {
        List<Route> routes = routeService.saveAll(routeRequests);
        return ResponseEntity.ok(routeResponseMapper.map(routes));
    }

    //--------> READ <--------\\
    @GetMapping
    public Page<RouteResponse> getRoutes(Pageable pageable) {
        Page<Route> routes = routeService.findAll(pageable);
        return routes.map(routeResponseMapper::map);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RouteResponse>> getAllRoutes() {
        List<Route> routes = routeService.findAll();
        return ResponseEntity.ok(routeResponseMapper.map(routes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> getAllRoutes(@PathVariable Long id) throws DataNotFoundException {
        Route route = routeService.findById(id);
        return ResponseEntity.ok(routeResponseMapper.map(route));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RouteResponse>> search(@RequestBody RouteSearchRequest routeSearchRequest, Pageable pageable) {
        Page<Route> routes = routeService.search(routeSearchRequest, pageable);
        return ResponseEntity.ok(routes.map(routeResponseMapper::map));
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}")
    public ResponseEntity<RouteResponse> update(@PathVariable Long id, @Valid @RequestBody RouteRequest routeRequest) throws DataNotFoundException, DuplicateEntryException {
        Route route = routeService.update(id, routeRequest);
        return ResponseEntity.ok(routeResponseMapper.map(route));
    }

}
