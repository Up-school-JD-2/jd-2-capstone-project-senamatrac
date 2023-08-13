package io.upschool.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class AirlineRequest {
    @NotBlank
    private String iataCode;
    @NotBlank
    private String name;
}
