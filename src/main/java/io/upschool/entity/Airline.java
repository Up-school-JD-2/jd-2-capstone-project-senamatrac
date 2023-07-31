package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "airline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String iataCode;

    @OneToMany(mappedBy = "airline", fetch = FetchType.LAZY)
    private Set<Airplane> airplaneTypes;

    @ManyToMany
    private Set<Route> routes;
}
