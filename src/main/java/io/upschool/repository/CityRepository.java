package io.upschool.repository;

import io.upschool.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    @Override
    List<City> findAll();
}
