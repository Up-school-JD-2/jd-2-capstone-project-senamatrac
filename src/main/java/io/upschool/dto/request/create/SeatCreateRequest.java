package io.upschool.dto.request.create;

import io.upschool.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"seatCode"})
public class SeatCreateRequest {
    @NotBlank
    private String seatCode;
    @NotNull
    private SeatType seatType;
}
