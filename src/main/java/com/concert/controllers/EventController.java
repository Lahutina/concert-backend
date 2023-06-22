package com.concert.controllers;

import com.concert.entities.Event;
import com.concert.entities.FilterData;
import com.concert.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private EventService eventService;

    @GetMapping("/{id}")
    public Event readEvent(@PathVariable Long id) {
        return eventService.read(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void createEvent(@RequestBody Event event) {
        eventService.create(event);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
        eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

    @PostMapping(value = "/image/{title}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@PathVariable String title, @RequestPart MultipartFile file) {
        eventService.uploadEventImage(title, file);
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String title) {
        return eventService.getEventImage(title);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.readAll();
    }

    @GetMapping("/pagination")
    public Page<Event> getPaginatedEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        return eventService.readAll(page, size);
    }

    @PostMapping("/filtered")
    public Page<Event> getFilteredEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size, @RequestBody FilterData filterData) {
        return eventService.getFiltered(page, size, filterData);
    }

    @GetMapping("/pagination/location")
    public Page<Event> readPaginatedByLocation(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size, @RequestParam String location) {
        return eventService.readAllByLocation(page, size, location);
    }
}
