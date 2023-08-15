package io.upschool.mapper.entity;

import io.upschool.dto.request.create.CreditCardPaymentCreateRequest;
import io.upschool.dto.request.search.PaymentSearchRequest;
import io.upschool.entity.CreditCardPayment;
import io.upschool.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "digits", qualifiedByName = "MaskedCreditCard")
    @Mapping(target = "owner", qualifiedByName = "MaskedOwner")
    CreditCardPayment map(CreditCardPaymentCreateRequest creditCard, BigDecimal tax, BigDecimal total);


    @Named("MaskedCreditCard")
    default String maskedCreditCard(String digits) {
        String first4digits = digits.substring(0, 4);
        String shouldMasked = digits.substring(4, digits.length() - 4);
        String last4digits = digits.substring(digits.length() - 4);

        String masked = shouldMasked.replaceAll("\\W?\\d\\W?", "*");
        return first4digits + masked + last4digits;
    }

    @Named("MaskedOwner")
    default String maskedOwnerName(String owner) {
        return owner.replaceAll("(?<=\\w{1})[^\\s]", "*");
    }

    Optional<Payment> map(PaymentSearchRequest paymentSearchRequest);
}
