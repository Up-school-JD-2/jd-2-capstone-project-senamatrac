package io.upschool.mapper.response;

import io.upschool.dto.response.CityResponse;
import io.upschool.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityResponseMapper {
    CityResponse map(City city);
}
