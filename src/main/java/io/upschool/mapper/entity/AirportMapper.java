package io.upschool.mapper.entity;

import io.upschool.dto.request.AirportRequest;
import io.upschool.dto.request.search.AirportSearchRequest;
import io.upschool.entity.Airport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CityMapper.class})
public interface AirportMapper {
    Airport map(AirportRequest airportRequest);
    List<Airport> map(List<AirportRequest> airportRequest);
    Airport map(AirportSearchRequest airportSearchRequest);
}
