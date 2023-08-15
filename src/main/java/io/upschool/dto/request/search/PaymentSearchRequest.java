package io.upschool.dto.request.search;

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
public class PaymentSearchRequest {
    private PaymentMethod paymentMethod;
    private BigDecimal total;
    private BigDecimal tax;
    private PaymentStatus status;
}
