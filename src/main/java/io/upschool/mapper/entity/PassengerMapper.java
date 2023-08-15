package io.upschool.mapper.entity;

import io.upschool.dto.request.create.PassengerCreateRequest;
import io.upschool.entity.Passenger;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PassengerMapper {
    Passenger map(PassengerCreateRequest passengerCreateRequest);
}
