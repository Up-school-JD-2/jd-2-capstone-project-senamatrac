package io.upschool.dto.request;

import io.upschool.enums.PaymentType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    @NotBlank
    private BigDecimal total;

    @NotBlank
    private BigDecimal tax;

    @NotBlank
    private PaymentType paymentType;

    @NotBlank
    private CreditCartRequest creditCart;
}
