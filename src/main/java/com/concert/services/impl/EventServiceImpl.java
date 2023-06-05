package com.concert.services.impl;

import com.concert.configuration.S3Props;
import com.concert.dao.EventDao;
import com.concert.entities.Event;
import com.concert.services.EventService;
import com.concert.services.S3Service;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;
    private final S3Service s3Service;
    private final S3Props s3Props;

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
    public Page<Event> readAll(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return eventDao.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        eventDao.findById(id).ifPresent(eventDao::delete);
    }

    @Override
    public void update(Long id, Event event) {
        if (read(id) != null) event.setId(id);
        eventDao.save(event);
    }

    @Override
    public byte[] getEventImage(String title) {
        return s3Service.getImages(s3Props.getS3bucket(), title);
    }

    @SneakyThrows
    @Override
    public void uploadEventImage(String title, MultipartFile file) {
        s3Service.putImage(s3Props.getS3bucket(), title, file.getBytes());
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        return eventDao.findByLocation(location);
    }

    @Override
    public Page<Event> readAllByLocation(int page, int size, String location) {
        List<Event> events = eventDao.findByLocation(location);
        return new PageImpl<>(events, Pageable.unpaged(), events.size());

    }
}
