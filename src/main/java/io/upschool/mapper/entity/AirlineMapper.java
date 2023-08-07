package io.upschool.mapper.entity;

import io.upschool.dto.request.AirlineRequest;
import io.upschool.dto.request.search.AirlineSearchRequest;
import io.upschool.entity.Airline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    Airline map(AirlineRequest airlineRequest);

    Airline map(AirlineSearchRequest airlineSearchRequest);
}
