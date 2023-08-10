package io.upschool.mapper.entity;

import io.upschool.dto.request.CreditCardRequest;
import io.upschool.entity.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    CreditCard map(CreditCardRequest creditCard);
}
