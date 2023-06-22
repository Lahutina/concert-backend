package com.concert.dao;

import com.concert.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationDao extends JpaRepository<City, Long> {
    @Query("SELECT name FROM City")
    List<String> getAllNames();
}
