package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "aircraft")
public class AircraftType extends BaseEntity {

    @Column(unique = true)
    private String iataCode;

    @Column
    private String model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aircraftType", cascade = CascadeType.ALL)
    private Set<Seat> seats;


}
