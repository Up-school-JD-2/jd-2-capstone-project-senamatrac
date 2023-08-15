package io.upschool.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirlineCreateRequest {
    @NotBlank
    private String iataCode;
    @NotBlank
    private String name;
}
