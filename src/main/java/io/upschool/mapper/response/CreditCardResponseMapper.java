package io.upschool.mapper.response;

import io.upschool.dto.response.CreditCardResponse;
import io.upschool.entity.CreditCardPayment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardResponseMapper {

    CreditCardResponse map(CreditCardPayment creditCardPayment);
}
