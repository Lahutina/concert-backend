package com.concert.controllers;

import com.concert.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
@AllArgsConstructor
public class LocationController {
    private LocationService locationService;

    @GetMapping
    public String findLocation(@RequestParam double latitude, @RequestParam double longitude) {
        return locationService.getLocation(latitude, longitude);
    }

    @GetMapping("/all")
    public List<String> getAll() {
        return locationService.getAll();
    }
}
