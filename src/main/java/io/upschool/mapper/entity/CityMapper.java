package io.upschool.mapper.entity;

import io.upschool.dto.request.create.CityCreateRequest;
import io.upschool.dto.request.search.CitySearchRequest;
import io.upschool.entity.City;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CountryMapper.class}, builder = @Builder(disableBuilder = true))
public interface CityMapper {
    City map(CityCreateRequest cityCreateRequest);

    City map(CitySearchRequest citySearchRequest);
}
