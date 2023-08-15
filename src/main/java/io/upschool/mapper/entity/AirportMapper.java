package io.upschool.mapper.entity;

import io.upschool.dto.request.create.AirportCreateRequest;
import io.upschool.dto.request.search.AirportSearchRequest;
import io.upschool.entity.Airport;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class}, builder = @Builder(disableBuilder = true))
public interface AirportMapper {
    Airport map(AirportCreateRequest airportCreateRequest);

    List<Airport> map(List<AirportCreateRequest> airportCreateRequest);

    Airport map(AirportSearchRequest airportSearchRequest);
}
