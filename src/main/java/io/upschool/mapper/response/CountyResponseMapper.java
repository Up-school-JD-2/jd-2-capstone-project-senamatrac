package io.upschool.mapper.response;

import io.upschool.dto.response.CountryResponse;
import io.upschool.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityResponseMapper.class})
public interface CountyResponseMapper {
    @Mapping(target = "cities",ignore = true)
    CountryResponse map(Country country);
    List<CountryResponse> map(List<Country> country);



}
