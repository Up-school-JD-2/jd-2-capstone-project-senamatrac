package io.upschool.mapper.entity;

import io.upschool.dto.request.CreditCardRequest;
import io.upschool.entity.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    @Mapping(target = "digits", qualifiedByName = "MaskedCreditCard")
    CreditCard map(CreditCardRequest creditCard);


    @Named("MaskedCreditCard")
    default String maskedCreditCard(String digits){
        String first4digits = digits.substring(0,4);
        String shouldMasked = digits.substring(4, digits.length()-4);
        String last4digits = digits.substring( digits.length()-4);

        String masked = shouldMasked.replaceAll("\\W?\\d\\W?","*");
        return first4digits + masked + last4digits;
    }
}
