package io.upschool.dto.request.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitySearchRequest {
    private String name;
    private String code;
    private CountrySearchRequest country;
}
