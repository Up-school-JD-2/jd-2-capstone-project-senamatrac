package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.Duration;

@Entity
@Table(name = "route", indexes = @Index(name = "UNQ_ROUTE_ORIGIN_DESTINATION", columnList = "origin_airport_id, destination_airport_id", unique = true))
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
@Where(clause = "deleted=false")
public class Route extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    @Column
    private Duration duration;


}
