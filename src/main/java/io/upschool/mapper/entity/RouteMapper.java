package io.upschool.mapper.entity;

import io.upschool.dto.request.search.RouteSearchRequest;
import io.upschool.entity.Route;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RouteMapper.class}, builder = @Builder(disableBuilder = true))
public interface RouteMapper {
    Route map(RouteSearchRequest routeSearchRequest);

    List<Route> map(List<RouteSearchRequest> routeSearchRequest);

}
