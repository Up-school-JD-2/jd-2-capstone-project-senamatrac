package io.upschool.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class PassengerRequest {
    @NotBlank
    private String identityNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
