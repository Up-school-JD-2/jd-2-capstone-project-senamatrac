package io.upschool.dto.response;

import io.upschool.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSeatPriceResponse {
    private Long id;
    private SeatType seatType;
    private BigDecimal price;
}
