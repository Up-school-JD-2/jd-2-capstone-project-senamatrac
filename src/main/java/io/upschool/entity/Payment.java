package io.upschool.entity;

import io.upschool.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private BigDecimal tax;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

}
