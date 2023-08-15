package io.upschool.controller;

import io.upschool.dto.request.create.RouteCreateRequest;
import io.upschool.dto.request.search.RouteSearchRequest;
import io.upschool.dto.response.BaseResponse;
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
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<BaseResponse<RouteResponse>> create(@Valid @RequestBody RouteCreateRequest routeCreateRequest) throws DataNotFoundException, DuplicateEntryException {
        Route route = routeService.save(routeCreateRequest);
        var response = BaseResponse.<RouteResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routeResponseMapper.map(route))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<RouteResponse>>> create(@Valid @RequestBody List<RouteCreateRequest> routeCreateRequests) throws DataNotFoundException, DuplicateEntryException {
        List<Route> routes = routeService.saveAll(routeCreateRequests);
        var response = BaseResponse.<List<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routeResponseMapper.map(routes))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> READ <--------\\
    @GetMapping
    public ResponseEntity<BaseResponse<Page<RouteResponse>>> getRoutes(Pageable pageable) {
        Page<Route> routes = routeService.findAll(pageable);
        var response = BaseResponse.<Page<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routes.map(routeResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<RouteResponse>>> getAllRoutes() {
        List<Route> routes = routeService.findAll();
        var response = BaseResponse.<List<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(routeResponseMapper.map(routes))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<RouteResponse>> getAllRoutes(@PathVariable Long id) throws DataNotFoundException {
        Route route = routeService.findById(id);
        var response = BaseResponse.<RouteResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(routeResponseMapper.map(route))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<BaseResponse<Page<RouteResponse>>> search(@Valid @RequestBody RouteSearchRequest routeSearchRequest, Pageable pageable) {
        Page<Route> routes = routeService.search(routeSearchRequest, pageable);
        var response = BaseResponse.<Page<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(routes.map(routeResponseMapper::map))
                .build();
        return ResponseEntity.ok(response);
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BaseResponse<RouteResponse>> cancel(@PathVariable Long id) throws DataNotFoundException {
        Route route = routeService.cancel(id);
        var response = BaseResponse.<RouteResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routeResponseMapper.map(route))
                .build();
        return ResponseEntity.ok(response);
    }
}
