package com.concert.controllers;

import com.concert.entities.Event;
import com.concert.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
public class EventController {
    private EventService eventService;

    @PostMapping
    public void createEvent(@RequestBody Event event) {
        eventService.create(event);
    }

    @GetMapping("/{id}")
    public Event readEvent(@PathVariable Long id) {
        return eventService.read(id);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
        eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

    @GetMapping
    public List<Event> readAllEvents() {
        return eventService.readAll();
    }
}
