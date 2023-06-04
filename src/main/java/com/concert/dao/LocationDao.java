package com.concert.dao;

import com.concert.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDao extends JpaRepository<City, Long> {

}
