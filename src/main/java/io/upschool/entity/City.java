package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


}
