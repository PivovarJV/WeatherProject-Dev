package org.example.service;

import org.example.dto.GeoDTO;
import org.example.dto.WeatherDTO;
import org.example.exception.ApiExceprion;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OpenWeatherAPIService {

    public WeatherDTO getWeatherByNameCity(double lat, double lon) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+
                            lon+"&appid=717c654c03ad05328fd336454ee6d644&units=metric"
                    , WeatherDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            handleHttpError(e);
        }
        return null;
    }

    public List<GeoDTO> getGeoByNameCity(String city) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<GeoDTO>> response= restTemplate.exchange("http://api.openweathermap.org/geo/1.0/direct?q="
                            + city + "&limit=5&appid=717c654c03ad05328fd336454ee6d644",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<GeoDTO>>() {});
            return response.getBody();
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            handleHttpError(e);
        }
        return null;
    }

    private void handleHttpError(HttpStatusCodeException e) {
        switch (e.getStatusCode().value()) {
            case 400 -> throw  new ApiExceprion("Неправильный запрос. В запросе отсутствуют некоторые обязательные" +
                    " параметры или некоторые " +
                    "параметры запроса имеют неверный формат или значения вне допустимого диапазона.");
            case 401 -> throw new ApiExceprion("API-токен не был предоставлен в запросе");
            case 404 -> throw new ApiExceprion("Ошибка 404 — Не найдено.");
            case 429 -> throw new ApiExceprion("Ошибка 429 — Слишком много запросов. Квота ключей запросов для предоставленного API к этому API была превышена.");
            case 500 -> throw new ApiExceprion("Ошибки 5xx — Неожиданная ошибка.");
            default -> throw new ApiExceprion("Неизвестная ошибка при запросе к API");
        }
    }
}
