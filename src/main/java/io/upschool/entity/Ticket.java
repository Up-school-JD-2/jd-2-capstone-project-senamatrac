package io.upschool.entity;

import io.upschool.enums.SeatType;
import io.upschool.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"ticketNumber"})
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticketNumber;

    @Column(nullable = false)
    private SeatType seatType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(cascade = CascadeType.PERSIST,optional = false)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.PERSIST,optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;


}
