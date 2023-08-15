package io.upschool.mapper.response;

import io.upschool.dto.response.PaymentResponse;
import io.upschool.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CreditCardResponseMapper.class})
public interface PaymentResponseMapper {
    PaymentResponse map(Payment payment);
}
