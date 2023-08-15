package io.upschool.dto.response;

import io.upschool.enums.PaymentMethod;
import io.upschool.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private BigDecimal total;
    private BigDecimal tax;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private CreditCardResponse creditCard;
}
