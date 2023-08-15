package io.upschool.service;

import io.upschool.entity.Payment;
import io.upschool.enums.PaymentStatus;
import io.upschool.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public Payment pay(Payment payment) {
        payment.setStatus(PaymentStatus.COMPLETED);
        return paymentRepository.save(payment);
    }
}
