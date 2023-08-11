package io.upschool.entity;

import io.upschool.enums.LegType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id","flightNumber"})
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String flightNumber;

    @Column(nullable = false)
    private LocalDate flightDate;

    @Column
    @Enumerated(EnumType.STRING)
    private LegType legType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aircraft_type_id")
    private AircraftType aircraftType;


    @ManyToOne(optional = false)
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.PERSIST)
    private Set<FlightSeatPrice> flightSeatPrices;

}
