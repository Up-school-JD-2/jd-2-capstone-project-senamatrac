package io.upschool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name="flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class CreditCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String expiredMonth;

    @Column(nullable = false)
    private String expiredYear;

    @Column( nullable = false)
    private String digits;

    @Column(nullable = false)
    private String ccv;
}
