package io.upschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "credit_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private int expiredMonth;

    @Column(nullable = false)
    private int expiredYear;

    @Column(nullable = false)
    private String digits;

    @Column(nullable = false)
    private String cvv;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
