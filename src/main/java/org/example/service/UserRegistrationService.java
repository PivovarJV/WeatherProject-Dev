package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dao.UserDAO;
import org.example.exception.LoginAlreadyTakenException;
import org.example.exception.PasswordMismatchException;
import org.example.model.User;
import org.springframework.stereotype.Service;

import static org.example.util.PasswordService.hashPassword;

@RequiredArgsConstructor
@Service
public class UserRegistrationService {

    private final UserDAO userDAO;

    @Transactional
    public void registerUser(String login, String password, String repeatPassword) {
        checkLoginValidity(login);
        checkPasswordValidity(password, repeatPassword);
        saveUser(login,password);
    }


    private void saveUser(String login, String password) {
        User user = new User(login, hashPassword(password));
        userDAO.saveUser(user);
    }

    private void checkPasswordValidity(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new PasswordMismatchException("Пароли не совпадают");
        }
    }

    private void checkLoginValidity(String login) {
        if (userDAO.isUserExist(login)) {
            throw new LoginAlreadyTakenException("Логин занят");
        }
    }
}
