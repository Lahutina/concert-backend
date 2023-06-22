package com.concert.dao;

import com.concert.entities.Event;
import com.concert.entities.FilterData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FiltersDao {
     Page<Event> findByFilters(Pageable pageable, FilterData filterData);
}
