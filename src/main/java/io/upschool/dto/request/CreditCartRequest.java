package io.upschool.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCartRequest {
    @NotBlank
    private String owner;

    @NotBlank
    private String expiredMonth;

    @NotBlank
    private String expiredYear;

    @NotBlank
    private String digits;

    @NotBlank
    private String ccv;
}
