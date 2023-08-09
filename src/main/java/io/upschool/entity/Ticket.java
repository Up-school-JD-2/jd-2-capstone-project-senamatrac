package io.upschool.entity;

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

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
