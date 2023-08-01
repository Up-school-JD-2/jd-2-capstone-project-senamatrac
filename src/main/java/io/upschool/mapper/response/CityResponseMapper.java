package io.upschool.mapper.response;

import io.upschool.dto.response.CityResponse;
import io.upschool.entity.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountyResponseMapper.class})
public interface CityResponseMapper {
    CityResponse map(City city);

    List<CityResponse> map(List<City> all);
}
