package io.upschool.mapper.entity;

import io.upschool.dto.request.PassengerRequest;
import io.upschool.entity.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger map(PassengerRequest passengerRequest);
}
