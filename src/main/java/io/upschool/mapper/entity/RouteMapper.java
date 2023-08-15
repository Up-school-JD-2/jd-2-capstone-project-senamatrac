package io.upschool.mapper.entity;

import io.upschool.dto.request.search.RouteSearchRequest;
import io.upschool.entity.Route;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RouteMapper.class}, builder = @Builder(disableBuilder = true))
public interface RouteMapper {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Route map(RouteSearchRequest routeSearchRequest);


}
