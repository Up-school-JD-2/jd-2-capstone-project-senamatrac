package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "aircraft")
@Getter
@Setter
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

    @Column
    private String model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aircraft", cascade = CascadeType.ALL)
    private Set<Seat> seats;


}
