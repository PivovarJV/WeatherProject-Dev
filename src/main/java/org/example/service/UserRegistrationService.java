package org.example.service;

import jakarta.transaction.Transactional;
import org.example.dao.UserDAO;
import org.example.exception.LoginAlreadyTakenException;
import org.example.exception.PasswordMismatchException;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserDAO userDAO;
    private final PasswordService passwordService;

    @Autowired
    public UserRegistrationService(UserDAO userDAO, PasswordService passwordService) {
        this.userDAO = userDAO;
        this.passwordService = passwordService;
    }

    @Transactional
    public void registerUser(String login, String password, String repeatPassword) {
        validateLogin(login);
        validatePassword(password, repeatPassword);
        saveUser(login,password);
    }


    private void saveUser(String login, String password) {
        User user = new User(login, passwordService.hashPassword(password));
        userDAO.saveUser(user);
    }

    private void validatePassword(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new PasswordMismatchException("Пароли не совпадают");
        }
    }

    private void validateLogin(String login) {
        if (userDAO.isUserExist(login)) {
            throw new LoginAlreadyTakenException("Логин занят");
        }
    }
}
