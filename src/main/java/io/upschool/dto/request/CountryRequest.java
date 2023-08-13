package io.upschool.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class CountryRequest {
    @NotBlank
    @Size(min = 3, max = 70, message = "The country name '${validatedValue}' must be between {min} and {max} characters long")
    private String name;
    @NotBlank
    private String code;
}
