package io.upschool.entity;

import io.upschool.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;

@Entity
@Table(name = "flight_seat_price")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "seatType"})
@Builder
@Audited
public class FlightSeatPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id")
    private Flight flight;


    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Column
    private BigDecimal price;

}
