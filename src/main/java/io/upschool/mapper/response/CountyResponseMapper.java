package io.upschool.mapper.response;

import io.upschool.dto.response.CountryResponse;
import io.upschool.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountyResponseMapper {

    CountryResponse map(Country country);

    List<CountryResponse> map(List<Country> country);


}
