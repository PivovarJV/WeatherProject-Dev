package org.example.service;

import org.example.dto.GeoDTO;
import org.example.dto.WeatherDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenWeatherAPIService {

    public WeatherDTO getWeatherByNameCity(double lat, double lon) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+
                        lon+"&appid=717c654c03ad05328fd336454ee6d644&units=metric"
                , WeatherDTO.class);
    }

    public List<GeoDTO> getGeoByNameCity(String city) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GeoDTO>> response= restTemplate.exchange("http://api.openweathermap.org/geo/1.0/direct?q="
                        + city + "&limit=5&appid=717c654c03ad05328fd336454ee6d644",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GeoDTO>>() {});
        return response.getBody();
    }
}
