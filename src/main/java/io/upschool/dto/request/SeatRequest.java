package io.upschool.dto.request;

import io.upschool.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatRequest {
    @NotBlank
    private String seatCode;
    @NotBlank
    private SeatType seatType;
}
