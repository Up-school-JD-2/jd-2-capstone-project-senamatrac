package io.upschool.dto.response;

import io.upschool.enums.RouteStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteResponse {
    private Long id;
    private RouteStatus status;
    private AirportResponse origin;
    private AirportResponse destination;
}
