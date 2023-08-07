package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "aircraft")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String iataCode;

    @Column(unique = true)
    private String model;

    @Column(nullable = false)
    private Integer maxSeat;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Seat> seats;

}
