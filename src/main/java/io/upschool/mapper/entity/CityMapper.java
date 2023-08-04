package io.upschool.mapper.entity;

import io.upschool.dto.request.CityRequest;
import io.upschool.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface CityMapper {
    City map(CityRequest cityRequest);
}
