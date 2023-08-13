package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@Entity
@Table(name = "passenger")
public class Passenger extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String identityNumber;

    @Column
    private String name;

    @Column
    private String surname;

}
