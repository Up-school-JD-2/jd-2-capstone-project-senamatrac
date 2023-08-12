package io.upschool.dto.request.search;

import io.upschool.enums.LegType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSearchRequest {
    private String flightNumber;
    private LocalDate flightDate;
    private LegType legType;
    private AirlineSearchRequest airline;
    private AircraftTypeSearchRequest aircraftType;
    private RouteSearchRequest route;
}
