package io.upschool.controller;

import io.upschool.dto.request.RouteRequest;
import io.upschool.dto.request.search.RouteSearchRequest;
import io.upschool.dto.response.BaseResponse;
import io.upschool.dto.response.RouteResponse;
import io.upschool.entity.Route;
import io.upschool.exception.DataCannotDelete;
import io.upschool.exception.DataNotFoundException;
import io.upschool.exception.DuplicateEntryException;
import io.upschool.mapper.response.RouteResponseMapper;
import io.upschool.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<RouteResponse> create(@Valid @RequestBody RouteRequest routeRequest) throws DataNotFoundException, DuplicateEntryException {
        Route route = routeService.save(routeRequest);
        return BaseResponse.<RouteResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routeResponseMapper.map(route))
                .build();
    }

    @PostMapping("/all")
    public BaseResponse<List<RouteResponse>> create(@Valid @RequestBody List<RouteRequest> routeRequests) throws DataNotFoundException, DuplicateEntryException {
        List<Route> routes = routeService.saveAll(routeRequests);
        return BaseResponse.<List<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routeResponseMapper.map(routes))
                .build();
    }

    //--------> READ <--------\\
    @GetMapping
    public BaseResponse<Page<RouteResponse>> getRoutes(Pageable pageable) {
        Page<Route> routes = routeService.findAll(pageable);
        return BaseResponse.<Page<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routes.map(routeResponseMapper::map))
                .build();
    }

    @GetMapping("/all")
    public BaseResponse<List<RouteResponse>> getAllRoutes() {
        List<Route> routes = routeService.findAll();
        return BaseResponse.<List<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(routeResponseMapper.map(routes))
                .build();
    }

    @GetMapping("/{id}")
    public BaseResponse<RouteResponse> getAllRoutes(@PathVariable Long id) throws DataNotFoundException {
        Route route = routeService.findById(id);
        return BaseResponse.<RouteResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(routeResponseMapper.map(route))
                .build();
    }

    @GetMapping("/search")
    public BaseResponse<Page<RouteResponse>> search(@RequestBody RouteSearchRequest routeSearchRequest, Pageable pageable) {
        Page<Route> routes = routeService.search(routeSearchRequest, pageable);
        return BaseResponse.<Page<RouteResponse>>builder()
                .isSuccess(true)
                .status(HttpStatus.OK.value())
                .responseBody(routes.map(routeResponseMapper::map))
                .build();
    }

    //--------> UPDATE <--------\\
    @PutMapping("/{id}/canceled")
    public BaseResponse<RouteResponse> cancel(@PathVariable Long id) throws DataNotFoundException {
        Route route = routeService.cancel(id);
        return BaseResponse.<RouteResponse>builder()
                .isSuccess(true)
                .status(HttpStatus.CREATED.value())
                .responseBody(routeResponseMapper.map(route))
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
