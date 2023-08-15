package io.upschool.dto.request.create;

import io.upschool.enums.SeatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = {"seatType"})
public class FlightSeatPriceCreateRequest {
    @NotNull
    private SeatType seatType;
    @NotNull
    @Min(1)
    private BigDecimal price;

}
