package io.upschool.mapper.entity;

import io.upschool.dto.request.create.AirlineCreateRequest;
import io.upschool.dto.request.search.AirlineSearchRequest;
import io.upschool.entity.Airline;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AirlineMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Airline map(AirlineCreateRequest airlineCreateRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Airline map(AirlineSearchRequest airlineSearchRequest);
}
