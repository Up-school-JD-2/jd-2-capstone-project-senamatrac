package io.upschool.mapper.response;

import io.upschool.dto.response.RouteResponse;
import io.upschool.entity.Route;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {AirportResponseMapper.class})
public interface RouteResponseMapper {
    RouteResponse map(Route route);
    List<RouteResponse> map(List<Route> route);
}
