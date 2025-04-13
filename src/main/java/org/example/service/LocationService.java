package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dao.LocationDAO;
import org.example.model.Location;
import org.example.model.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationDAO locationDAO;

    @Transactional
    public void deleteLocation(int id, User user) {
        Location location = getLocationById(id);
        if (location.getUser().getId() == user.getId()) {
            locationDAO.deleteLocation(location);
        }
    }

    @Transactional
    public void saveLocation(Location location) {
        locationDAO.saveLocation(location);
    }

    public Location getLocationById(int id) {
        return locationDAO.getLocationById(id);
    }
}
