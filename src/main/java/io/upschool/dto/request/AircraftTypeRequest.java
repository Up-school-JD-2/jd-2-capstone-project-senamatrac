package io.upschool.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AircraftTypeRequest {
    @NotBlank
    private String iataCode;
    @NotBlank
    private String model;
    @Valid
    @NotEmpty
    private Set<SeatRequest> seats;
}
