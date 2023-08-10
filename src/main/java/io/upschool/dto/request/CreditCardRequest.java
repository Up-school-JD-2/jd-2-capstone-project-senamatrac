package io.upschool.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardRequest {
    @NotBlank
    private String owner;


    @Pattern(regexp = "(1|2|3|4|5|6|7|8|9|01|02|03|04|05|06|07|08|09|10|11|12|)", message = " The expired month is invalid")
    private String expiredMonth;


    @Pattern(regexp = "^(\\d{4})$", message = " The expired year is invalid")
    private String expiredYear;


    @Pattern(regexp = "^(\\d{4}\\W?\\d{4}\\W?\\d{4}\\W?\\d{4})$", message = " The credit card number is invalid")
    private String digits;

    @NotBlank
    @Pattern(regexp = "^(\\d{3})$", message = " The cvv is invalid")
    private String cvv;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String expiredDate;
    @AssertTrue(message = " The credit card has expired")
    private boolean isExpiredDate() {
        LocalDate now = LocalDate.now().withDayOfMonth(1);
        LocalDate expiredDate = LocalDate.of(Integer.parseInt(expiredYear), Integer.parseInt(expiredMonth), 1);
        return !now.isAfter(expiredDate);
    }
}
