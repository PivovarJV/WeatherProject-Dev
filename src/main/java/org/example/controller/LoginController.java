package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.AuthService;
import org.example.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final UserLoginService userLoginService;
    private final AuthService authService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-in";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpServletResponse response) {
        userLoginService.userLogin(user.getLogin(), user.getPassword());
        return authService.setSessionAndCookies(user, response);
    }
}