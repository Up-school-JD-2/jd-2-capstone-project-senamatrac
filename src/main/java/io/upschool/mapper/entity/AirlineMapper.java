package io.upschool.mapper.entity;

import io.upschool.dto.request.create.AirlineCreateRequest;
import io.upschool.dto.request.search.AirlineSearchRequest;
import io.upschool.entity.Airline;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AirlineMapper {
    Airline map(AirlineCreateRequest airlineCreateRequest);

    Airline map(AirlineSearchRequest airlineSearchRequest);
}
