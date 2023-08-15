package io.upschool.dto.request.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSearchRequest {
    private Long id;
    private AirportSearchRequest origin;
    private AirportSearchRequest destination;
    private Duration duration;
}
