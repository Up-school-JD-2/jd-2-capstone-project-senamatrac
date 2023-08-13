package io.upschool.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Duration;

@Getter
@Setter
public class RouteRequest {
    @NotNull
    private Long originAirportId;

    @NotNull
    private Long destinationAirportId;

    @NotNull
    private Duration duration;

}
