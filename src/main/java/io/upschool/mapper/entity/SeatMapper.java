package io.upschool.mapper.entity;

import io.upschool.dto.request.SeatRequest;
import io.upschool.entity.Seat;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    Seat map(SeatRequest seatRequest);

    List<Seat> map(List<SeatRequest> seatRequest);
    Set<Seat> map(Set<SeatRequest> seatRequest);
}
