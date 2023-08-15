package io.upschool.entity;

import io.upschool.enums.SeatType;
import io.upschool.enums.TicketStatus;
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
@Table(name = "ticket")
public class Ticket extends BaseEntity {
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

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "payment_id")
    private Payment payment;


}
