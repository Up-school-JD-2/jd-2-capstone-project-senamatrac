package io.upschool.mapper.response;

import io.upschool.dto.response.AirportResponse;
import io.upschool.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityResponseMapper.class})
public interface AirportResponseMapper {
    @Mapping(target = "city", source = "city")
    AirportResponse map(Airport airport);

    List<AirportResponse> map(List<Airport> airports);
}
