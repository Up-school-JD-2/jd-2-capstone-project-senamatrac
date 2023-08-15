package io.upschool.entity;

import io.upschool.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "seat", indexes = @Index(name = "UNQ_SEAT_AIRCRAFT_ID_SEAT_CODE", columnList = "aircraft_id, seat_code", unique = true))
public class Seat extends BaseEntity {

    @Column(name = "seat_code")
    private String seatCode;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aircraft_id")
    private AircraftType aircraftType;
}
