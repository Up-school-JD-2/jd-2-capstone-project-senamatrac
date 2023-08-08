package io.upschool.entity;

import io.upschool.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Set;

@Entity
@Table(name = "seat",indexes = @Index(name = "UNQ_SEAT_AIRCRAFT_ID_SEAT_CODE",columnList = "aircraft_id, seat_code"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"seatCode","seatType"})
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "seat_code")
    private String seatCode;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;
}
