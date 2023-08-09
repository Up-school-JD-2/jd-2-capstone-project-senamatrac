package io.upschool.mapper.response;

import io.upschool.dto.response.PassengerResponse;
import io.upschool.entity.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerResponseMapper {
    PassengerResponse map(Passenger passenger);
}
