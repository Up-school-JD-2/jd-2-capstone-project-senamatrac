package io.upschool.dto.request;

import io.upschool.entity.Aircraft;
import io.upschool.enums.SeatType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Long aircraftId;
}
