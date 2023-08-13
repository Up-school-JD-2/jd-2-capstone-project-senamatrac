package io.upschool.entity;

import io.upschool.enums.RouteStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.Duration;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "route", indexes = @Index(name = "UNQ_ROUTE_ORIGIN_DESTINATION", columnList = "origin_airport_id, destination_airport_id", unique = true))
public class Route extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    @Column
    private Duration duration;

    @Column
    @Enumerated(EnumType.STRING)
    private RouteStatus status;
}


