package io.upschool.dto.request.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftTypeSearchRequest {
    private Long id;
    private String iataCode;
    private String model;
    private Integer maxSeat;
}
