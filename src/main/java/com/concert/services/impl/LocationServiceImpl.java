package com.concert.services.impl;

import com.concert.dao.LocationDao;
import com.concert.entities.City;
import com.concert.services.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationDao locationDao;
    @Override
    public String getLocation(double latitude, double longitude) {
        City city = determineCityByLocation(latitude, longitude);
        return city.getName();
    }

    private City determineCityByLocation(double latitude, double longitude) {
        City closestCity = null;
        double minDistance = Double.MAX_VALUE;

        List<City> cityList = locationDao.findAll();

        for (City city : cityList) {
            double distance = calculateDistance(latitude, longitude, city);
            if (distance < minDistance) {
                minDistance = distance;
                closestCity = city;
            }
        }
        return closestCity;
    }

    private double calculateDistance(double latitude, double longitude, City city) {
        double earthRadius = 6371;

        double latDistance = Math.toRadians(city.getLatitude() - latitude);
        double lonDistance = Math.toRadians(city.getLongitude() - longitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(city.getLongitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}
