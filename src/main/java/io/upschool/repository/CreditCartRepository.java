package io.upschool.repository;

import io.upschool.entity.CreditCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCartRepository extends JpaRepository<CreditCart,Long> {
    @Override
    List<CreditCart> findAll();
}
