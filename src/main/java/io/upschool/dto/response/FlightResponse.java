package io.upschool.dto.response;

import io.upschool.enums.FlightStatus;
import io.upschool.enums.LegType;
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
public class FlightResponse {
    private Long id;
    private String flightNumber;
    private LocalDate flightDate;
    private LegType legType;
    private FlightStatus status;
    private AirlineResponse airline;
    private RouteResponse route;
    private Set<FlightSeatPriceResponse> flightSeatPrices;
}
