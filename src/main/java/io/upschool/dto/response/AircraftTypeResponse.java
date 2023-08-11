package io.upschool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftTypeResponse {
    private String iataCode;
    private String model;
    private List<SeatResponse> seats;
}
