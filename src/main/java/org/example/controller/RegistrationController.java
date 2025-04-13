package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserRegistrationDTO;
import org.example.service.UserRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("userDTO", new UserRegistrationDTO());
        return "sign-up";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userDTO") UserRegistrationDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        userRegistrationService.registerUser(userDTO.getLogin(), userDTO.getPassword(), userDTO.getRepeatPassword());
        return "redirect:/login";
    }
}
