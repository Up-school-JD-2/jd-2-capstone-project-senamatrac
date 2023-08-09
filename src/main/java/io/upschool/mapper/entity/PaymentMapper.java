package io.upschool.mapper.entity;

import io.upschool.dto.request.PaymentRequest;
import io.upschool.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CreditCardMapper.class})
public interface PaymentMapper {
    Payment map(PaymentRequest paymentRequest);
}
