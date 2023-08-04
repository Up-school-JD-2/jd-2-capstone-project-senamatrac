package io.upschool.repository;

import io.upschool.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

    public List<Country> findAllByIsDeleted(Boolean isDeleted);
    public Page<Country> findAllByIsDeleted(Boolean isDeleted, Pageable pageable);
    public Optional<Country> findByCode(String code);

    boolean existsByCode(String code);
}
