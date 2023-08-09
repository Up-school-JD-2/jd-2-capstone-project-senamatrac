package io.upschool.mapper.entity;

import io.upschool.dto.request.CreditCardRequest;
import io.upschool.entity.CreditCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    CreditCard map(CreditCardRequest creditCard);
}
