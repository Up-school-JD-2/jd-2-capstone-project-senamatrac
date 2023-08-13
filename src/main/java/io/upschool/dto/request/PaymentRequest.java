package io.upschool.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.upschool.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "paymentMethod")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditCardPaymentRequest.class, name = "CREDIT_CARD"),
        @JsonSubTypes.Type(value = CashPaymentRequest.class, name = "CASH")
})
public  abstract class PaymentRequest {
    @NotNull
    private PaymentMethod paymentMethod;
}
