package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.GeoDTO;
import org.example.dto.SearchDTO;
import org.example.model.Location;
import org.example.model.User;
import org.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SearchController {

    private final AuthService authService;
    private final OpenWeatherAPIService openWeatherAPIService;
    private final LocationService locationService;
    private final WeatherService weatherService;

    @PostMapping("/search-results")
    public String showSearchPage(@Valid @ModelAttribute("searchDTO") SearchDTO searchDTO,
                                 BindingResult bindingResult,
                                 Model model,
                                 HttpServletRequest request) {
        User user = authService.getUserFromRequest(request);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("searchDTO", searchDTO);
            return "index";
        }
        List<GeoDTO> geoList = openWeatherAPIService.getGeoByNameCity(searchDTO.getCity());
        geoList = weatherService.checkLocationByUserAndGeo(geoList, user);

        model.addAttribute("user", user);
        model.addAttribute("geoList", geoList);
        model.addAttribute("searchDTO", new SearchDTO());
        return "search-results";
    }

    @PostMapping("/search-resultss")
    public String searchResults(@ModelAttribute GeoDTO geoDTO, Model model, HttpServletRequest request) {
        User user = authService.getUserFromRequest(request);
        model.addAttribute("user", user);
        Location location = new Location(geoDTO.getName(), user, geoDTO.getLat(), geoDTO.getLon());
        locationService.saveLocation(location);
        return "redirect:/";
    }
}