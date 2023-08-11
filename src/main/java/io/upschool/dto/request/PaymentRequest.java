package io.upschool.dto.request;

import io.upschool.enums.PaymentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private BigDecimal total;

    @NotNull
    private BigDecimal tax;

    @NotNull
    private PaymentType paymentType;

    @Valid
    @NotNull
    private CreditCardRequest creditCard;


}
