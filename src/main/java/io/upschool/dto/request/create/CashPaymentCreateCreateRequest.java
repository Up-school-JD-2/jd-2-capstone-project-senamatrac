package io.upschool.dto.request.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashPaymentCreateCreateRequest extends PaymentCreateRequest {
    private String senderIBAN;
}
