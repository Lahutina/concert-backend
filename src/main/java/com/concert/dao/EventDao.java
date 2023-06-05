package com.concert.dao;

import com.concert.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventDao extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.location = :cityName")
    List<Event> findByLocation(String cityName);
}
