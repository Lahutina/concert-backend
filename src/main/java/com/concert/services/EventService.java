package com.concert.services;

import com.concert.entities.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    void create(Event event);

    Event read(Long id);

    List<Event> readAll();

    void delete(Long id);

    void update(Long id, Event event);

    String getEventImage(Long eventId);

    void uploadEventImage(Long eventId, MultipartFile file);
}
