package io.upschool.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityRequest {
    @NotBlank
    @Size(min = 3, max = 70 ,message = "The city name '${validatedValue}' must be between {min} and {max} characters long")
    private String name;
    @NotBlank
    private String code;
    @NotBlank
    private Long countryId;
}
