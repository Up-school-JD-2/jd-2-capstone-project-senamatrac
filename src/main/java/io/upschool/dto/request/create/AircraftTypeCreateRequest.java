package io.upschool.dto.request.create;

import io.upschool.validation.FirstOrder;
import io.upschool.validation.SecondOrder;
import io.upschool.validation.UniqueValidation;
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@GroupSequence({AircraftTypeCreateRequest.class, FirstOrder.class, SecondOrder.class})
public class AircraftTypeCreateRequest {
    @NotBlank
    private String iataCode;
    @NotBlank
    private String model;

    @NotEmpty(message = "The aircraft's seats should be assign.", groups = FirstOrder.class)
    @Valid
    @UniqueValidation(groups = SecondOrder.class)
    private Set<SeatCreateRequest> seats;
}
