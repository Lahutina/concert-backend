package com.concert.services.impl;

import com.concert.dao.EventDao;
import com.concert.entities.Event;
import com.concert.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;

    @Override
    public void create(Event event) {
        eventDao.save(event);
    }

    @Override
    public Event read(Long id) {
        return eventDao.findById(id).orElse(null);
    }

    @Override
    public List<Event> readAll() {
        return eventDao.findAll();
    }

    @Override
    public void delete(Long id) {
        eventDao.findById(id).ifPresent(eventDao::delete);
    }

    @Override
    public void update(Event event) {
        eventDao.save(event);
    }
}
