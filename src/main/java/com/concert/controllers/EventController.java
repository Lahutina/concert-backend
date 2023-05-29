package com.concert.controllers;

import com.concert.entities.Event;
import com.concert.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private EventService eventService;

    @GetMapping
    public List<Event> readAllEvents() {
        return eventService.readAll();
    }

    @GetMapping("/{id}")
    public Event readEvent(@PathVariable Long id) {
        return eventService.read(id);
    }

    @PostMapping
    public void createEvent(@RequestBody Event event) {
        eventService.create(event);
    }

    @PostMapping(value = "/image/{eventId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@PathVariable Long eventId, @RequestParam("file") MultipartFile file) {
        eventService.uploadEventImage(eventId, file);
    }

    @GetMapping(value = "/image/{eventId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getImage(@PathVariable Long eventId) {
        return eventService.getEventImage(eventId);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
        eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }


}
