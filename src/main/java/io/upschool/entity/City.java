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
public class City extends SoftDeleteEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


}
