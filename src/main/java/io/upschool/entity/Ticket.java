package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="ticket")
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

    @Column(nullable = false,unique = true)
    private String ticketNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger")
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Payment payment;

}
