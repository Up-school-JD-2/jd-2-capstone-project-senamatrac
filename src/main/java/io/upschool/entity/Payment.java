package io.upschool.entity;

import io.upschool.enums.PaymentMethod;
import io.upschool.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity
@Table(name = "payment")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "payment_method",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Payment extends BaseEntity {

    @Column(name = "payment_method", nullable = false,insertable=false, updatable=false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private BigDecimal tax;

    @Column(nullable = false)
    private PaymentStatus status;


}
