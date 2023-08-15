package io.upschool.mapper.entity;

import io.upschool.dto.request.create.PassengerCreateRequest;
import io.upschool.entity.Passenger;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PassengerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Passenger map(PassengerCreateRequest passengerCreateRequest);
}
