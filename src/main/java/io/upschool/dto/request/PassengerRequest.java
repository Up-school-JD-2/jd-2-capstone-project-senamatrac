package io.upschool.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerRequest {
    @NotBlank
    private String identityNumber;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
