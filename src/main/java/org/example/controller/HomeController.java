package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.dto.SearchDTO;
import org.example.model.User;
import org.example.service.AuthService;
import org.example.service.LocationService;
import org.example.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final AuthService authService;
    private final WeatherService weatherService;
    private final LocationService locationService;

    @GetMapping("/")
    public String showHomePage(Model model, HttpServletRequest request) {
        User user = authService.getUserFromRequest(request);
        model.addAttribute("user", user);
        model.addAttribute("searchDTO", new SearchDTO());
        model.addAttribute("weatherDTOList", weatherService.convertLocationsToWeather(user));
        return "index";
    }

    @PostMapping("/delete")
    public String deleteCity(@RequestParam("locationId") int id, HttpServletRequest request) {
        User user = authService.getUserFromRequest(request);
        locationService.deleteLocation(id, user);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logoutDeleteCookies(request, response);
        return "redirect:/login";
    }
}
