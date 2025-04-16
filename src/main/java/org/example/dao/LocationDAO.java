package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.model.Location;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveLocation(Location location) {
        entityManager.persist(location);
    }

    public List<Location> getListLocationByUser(User user) {
        String query = "From Location l WHERE l.user.id = :userId";
        return entityManager.createQuery(query, Location.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }

    public void deleteLocation(Location location) {
        entityManager.remove(location);
    }

    public Location getLocationById(int id) {
        return entityManager.find(Location.class, id);
    }

    public boolean checkLocationByUserAndGeo(User user, double lat, double lon) {
        String query = "From Location l WHERE l.user.id = :userId AND l.latitude = :lat" +
                " AND l.longitude = :lon";
        return !entityManager.createQuery(query, Location.class)
                .setParameter("userId", user.getId())
                .setParameter("lat", lat)
                .setParameter("lon", lon)
                .getResultList().isEmpty();
    }
}