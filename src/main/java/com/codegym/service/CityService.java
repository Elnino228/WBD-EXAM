package com.codegym.service;

import com.codegym.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    Page<City> findAll(Pageable pageable);

    Page<City> findAllByNameContaining(String city, Pageable pageable);

    City findById(Long id);

    void save(City city);

    void delete(Long id);
}
