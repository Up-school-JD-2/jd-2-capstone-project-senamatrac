package io.upschool.entity;

import io.upschool.enums.PaymentMethod;
import io.upschool.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity
@Table(name = "credit_card")
@DiscriminatorValue("CREDIT_CARD")
public class CreditCardPayment extends Payment {

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

    @Builder
    public CreditCardPayment( BigDecimal total, BigDecimal tax, PaymentStatus status, String owner, int expiredMonth, int expiredYear, String digits, String cvv) {
        super(PaymentMethod.CREDIT_CARD, total, tax, status);
        this.owner = owner;
        this.expiredMonth = expiredMonth;
        this.expiredYear = expiredYear;
        this.digits = digits;
        this.cvv = cvv;
    }
}
