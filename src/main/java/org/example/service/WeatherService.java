package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.LocationDAO;
import org.example.dto.GeoDTO;
import org.example.dto.WeatherDTO;
import org.example.model.Location;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final LocationDAO locationDAO;
    private final OpenWeatherAPIService openWeatherAPIService;

    public List<WeatherDTO> convertLocationsToWeather(User user) {
        List<Location> locationList = locationDAO.getListLocationByUser(user);
        List<WeatherDTO> weatherDTOList = new ArrayList<>();
        for (Location location : locationList) {
            WeatherDTO weatherDTO = openWeatherAPIService.getWeatherByNameCity(location.getLatitude(), location.getLongitude());
            weatherDTO.setLocationId(location.getId());
            weatherDTO.setName(location.getName());
            weatherDTOList.add(weatherDTO);
        }
        return weatherDTOList;
    }

    public List<GeoDTO> checkLocationByUserAndGeo(List<GeoDTO> listGeo, User user) {
        listGeo.removeIf(geo -> locationDAO.checkLocationByUserAndGeo(user, geo.getLat(), geo.getLon()));
        return listGeo;
    }
}
