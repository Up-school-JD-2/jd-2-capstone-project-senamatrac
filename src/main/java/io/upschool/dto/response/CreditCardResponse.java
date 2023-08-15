package io.upschool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardResponse {
    private Long id;
    private String owner;
    private String expiredMonth;
    private String expiredYear;
    private String digits;
    private String ccv;
}
