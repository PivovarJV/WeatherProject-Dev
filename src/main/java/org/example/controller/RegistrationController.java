package org.example.controller;

import org.example.dto.UserRegistrationDTO;
import org.example.model.User;
import org.example.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public RegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userDTO", new UserRegistrationDTO());
        return "sign-up";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userDTO") UserRegistrationDTO userDTO) {
        userRegistrationService.registerUser(userDTO.getLogin(), userDTO.getPassword(), userDTO.getRepeatPassword());
        return "redirect:/login";
    }
}
