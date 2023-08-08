package io.upschool.dto.request;

import io.upschool.enums.SeatType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"seatType"})
@Builder
public class FlightSeatPriceRequest {
    @NotBlank
    private SeatType seatType;
    @NotNull
    @Min(1)
    private BigDecimal price;
}
