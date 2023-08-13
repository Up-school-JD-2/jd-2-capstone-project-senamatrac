package io.upschool.dto.request;

import io.upschool.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class SeatRequest {
    @NotBlank
    private String seatCode;
    @NotNull
    private SeatType seatType;
}
