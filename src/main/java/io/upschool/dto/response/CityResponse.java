package io.upschool.dto.response;

import io.upschool.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {
    private Long id;
    private String name;
    private String code;
    private CountryResponse country;
}
