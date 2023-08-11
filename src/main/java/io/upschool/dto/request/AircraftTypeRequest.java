package io.upschool.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftTypeRequest {
    @NotBlank
    @Size(min = 3, max = 70, message = "The country name '${validatedValue}' must be between {min} and {max} characters long")
    private String iataCode;
    @NotBlank
    private String model;
    @NotNull
    private Integer maxSeat;
    @Valid
    @NotEmpty
    private Set<SeatRequest> seats;
}
