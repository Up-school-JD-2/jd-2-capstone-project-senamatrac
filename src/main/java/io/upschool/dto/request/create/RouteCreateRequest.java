package io.upschool.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class RouteCreateRequest {
    @NotNull
    private Long originAirportId;

    @NotNull
    private Long destinationAirportId;

    @NotNull
    private Duration duration;

}
