package io.upschool.dto.request.create;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.upschool.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//JSON POLYMORPHIC
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "paymentMethod",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditCardPaymentCreateRequest.class, name = "CREDIT_CARD"),
        @JsonSubTypes.Type(value = CashPaymentCreateCreateRequest.class, name = "CASH")
})
public abstract class PaymentCreateRequest {

    private PaymentMethod paymentMethod;
}
