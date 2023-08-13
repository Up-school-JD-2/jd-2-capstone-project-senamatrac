package io.upschool.entity;

import io.upschool.enums.FlightStatus;
import io.upschool.enums.FlightType;
import io.upschool.enums.LegType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id","flightNumber"})
@Builder
@Audited
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String flightNumber;

    @Column(nullable = false)
    private LocalDateTime flightDate;

    @Column
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    private FlightType flightType;

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
