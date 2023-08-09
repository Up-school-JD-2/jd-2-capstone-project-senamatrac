package io.upschool.repository;

import io.upschool.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Override
    List<CreditCard> findAll();
}
