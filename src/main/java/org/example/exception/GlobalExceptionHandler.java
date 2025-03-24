package org.example.exception;

import org.example.dto.UserRegistrationDTO;
import org.example.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginAlreadyTakenException.class)
    public String handleLoginAlreadyTakenException(LoginAlreadyTakenException e, Model model) {
        model.addAttribute("loginAlreadyTakenException", e.getMessage());
        model.addAttribute("userDTO", new UserRegistrationDTO());
        return "sign-up";
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public String handlePasswordMismatchException(PasswordMismatchException e, Model model) {
        model.addAttribute("passwordMismatchException", e.getMessage());
        model.addAttribute("userDTO", new UserRegistrationDTO());
        return "sign-up";
    }

    @ExceptionHandler(LoginNotFoundException.class)
    public String handleLoginNotFoundException(LoginNotFoundException e, Model model) {
        model.addAttribute("loginNotFoundException", e.getMessage());
        model.addAttribute("user", new User());
        return "sign-in";
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public String handleInvalidPasswordException(InvalidPasswordException e, Model model) {
        model.addAttribute("invalidPasswordException", e.getMessage());
        model.addAttribute("user", new User());
        return "sign-in";
    }
}
