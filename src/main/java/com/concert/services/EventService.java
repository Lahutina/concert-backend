package com.concert.services;

import com.concert.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    void create(Event event);

    Event read(Long id);

    List<Event> readAll();

    Page<Event> readAll(int page, int size);

    void delete(Long id);

    void update(Long id, Event event);

    byte[] getEventImage(String title);

    void uploadEventImage(String title, MultipartFile file);
}
