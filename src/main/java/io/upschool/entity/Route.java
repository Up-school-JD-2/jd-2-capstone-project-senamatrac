package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "route", columnNames = { "origin_airport_id", "destination_airport_id" }))
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "origin_airport_id")
    @ManyToOne
    private Airport origin;

    @Column(name = "destination_airport_id")
    @ManyToOne
    private Airport destination;


}
