package io.upschool.mapper.entity;

import io.upschool.dto.request.create.CountryCreateRequest;
import io.upschool.dto.request.search.CountrySearchRequest;
import io.upschool.entity.Country;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CityMapper.class}, builder = @Builder(disableBuilder = true))
public interface CountryMapper {
    Country map(CountryCreateRequest countryCreateRequest);

    Country map(CountrySearchRequest countryRequest);
}
