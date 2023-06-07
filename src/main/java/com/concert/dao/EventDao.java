package com.concert.dao;

import com.concert.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Long>, FiltersDao {

    @Query("SELECT e FROM Event e WHERE e.location = :cityName")
    Page<Event> findByLocation(String cityName, Pageable pageable);
}
