package io.upschool.entity;

import io.upschool.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "flight_seat_price")
public class FlightSeatPrice extends BaseEntity{

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id")
    private Flight flight;


    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Column
    private BigDecimal price;

}
