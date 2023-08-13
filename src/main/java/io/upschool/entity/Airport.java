package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "airport")
public class Airport extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String iataCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id")
    private City city;
}
