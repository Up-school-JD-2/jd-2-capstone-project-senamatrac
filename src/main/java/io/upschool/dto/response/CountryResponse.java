package io.upschool.dto.response;

import io.upschool.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryResponse {
    private Long id;
    private String name;
    private String code;
    private Set<City> cities;
}
