package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
