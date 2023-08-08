package io.upschool.entity;

import io.upschool.enums.LegType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private LocalDate flightDate;

    @ManyToOne
    @JoinColumn(name = "airline_id",nullable = false)
    private Airline airline;

    @Column
    @Enumerated(EnumType.STRING)
    private LegType legType;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;



}
