package io.upschool.mapper.entity;

import io.upschool.dto.request.CountryRequest;
import io.upschool.dto.request.search.CountrySearchRequest;
import io.upschool.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface CountryMapper {
    Country map(CountrySearchRequest countryRequest);
    Country map(CountryRequest countryRequest);
}
