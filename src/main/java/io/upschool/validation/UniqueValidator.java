package io.upschool.validation;

import io.upschool.dto.request.create.SeatCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.stream.Collectors;

public class UniqueValidator implements ConstraintValidator<UniqueValidation, Collection<SeatCreateRequest>> {
    @Override
    public boolean isValid(Collection<SeatCreateRequest> value, ConstraintValidatorContext context) {
        if (value.isEmpty())
            return false;
        var violations = value.stream()
                .collect(Collectors.groupingBy(SeatCreateRequest::getSeatCode, Collectors.counting()))
                .entrySet()
                .stream().filter(x -> x.getValue() > 1).toList();
        return false;
    }
}
