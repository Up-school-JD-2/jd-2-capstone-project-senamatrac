package io.upschool.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerCreateRequest {
    @NotBlank
    private String identityNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
