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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "flight")
public class Flight extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String flightNumber;

    @Column(nullable = false)
    private LocalDate flightDate;

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
