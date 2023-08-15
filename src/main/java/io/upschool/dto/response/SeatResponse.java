package io.upschool.dto.response;

import io.upschool.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatResponse {
    private Long id;
    private String seatCode;
    private SeatType seatType;
    private Boolean reserved;
}
