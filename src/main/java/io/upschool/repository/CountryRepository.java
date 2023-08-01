package io.upschool.repository;

import io.upschool.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    public Optional<Country> findByCode(String code);

    boolean existsByCode(String code);
}
