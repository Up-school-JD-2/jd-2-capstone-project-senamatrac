package io.upschool.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityCreateRequest {
    @NotBlank
    @Size(min = 3, max = 70, message = "The city name '${validatedValue}' must be between {min} and {max} characters long")
    private String name;
    @NotBlank
    private String code;
    @NotNull
    private Long countryId;
}
