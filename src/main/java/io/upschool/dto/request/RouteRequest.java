package io.upschool.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteRequest {
    @NotNull
    private Long originAirportId;

    @NotNull
    private Long destinationAirportId;

    @NotNull
    private Duration duration;

}
