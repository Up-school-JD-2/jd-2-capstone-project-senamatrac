package io.upschool.dto.request;

import io.upschool.enums.LegType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightRequest {

    @NotBlank
    private String flightNumber;
    @NotBlank
    private LocalDate flightDate;
    @NotBlank
    private Long airlineId;
    @NotBlank
    private LegType legType;
    @NotBlank
    private Long routeId;
    @Valid
    @NotEmpty
    private Set<FlightSeatPriceRequest> flightSeatPrice;
}
