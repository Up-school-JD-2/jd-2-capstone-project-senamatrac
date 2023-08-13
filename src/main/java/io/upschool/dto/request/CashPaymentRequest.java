package io.upschool.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashPaymentRequest extends PaymentRequest {
    private String senderIBAN;
}
