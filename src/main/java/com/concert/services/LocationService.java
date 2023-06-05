package com.concert.services;

import java.util.List;

public interface LocationService {
    String getLocation(double latitude, double longitude);

    List<String> getAll();
}
