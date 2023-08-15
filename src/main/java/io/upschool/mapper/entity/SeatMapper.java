package io.upschool.mapper.entity;

import io.upschool.dto.request.create.SeatCreateRequest;
import io.upschool.entity.Seat;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SeatMapper {
    Seat map(SeatCreateRequest seatCreateRequest);

    List<Seat> map(List<SeatCreateRequest> seatCreateRequest);

    Set<Seat> map(Set<SeatCreateRequest> seatCreateRequest);
}
