package io.upschool.dto.request;

import io.upschool.enums.SeatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = {"seatType"})
public class FlightSeatPriceRequest {
    @NotNull
    private SeatType seatType;
    @NotNull
    @Min(1)
    private BigDecimal price;

}
