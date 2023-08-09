package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passenger")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"identityNumber"})
@Builder
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identityNumber;

    @Column
    private String name;

    @Column
    private String surname;

}
