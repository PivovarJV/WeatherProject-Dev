package org.example.service;

import org.example.dao.UserDAO;
import org.example.exception.InvalidPasswordException;
import org.example.exception.LoginAlreadyTakenException;
import org.example.exception.LoginNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private final UserDAO userDAO;
    private final PasswordService passwordService;

    @Autowired
    public UserLoginService(UserDAO userDAO, PasswordService passwordService) {
        this.userDAO = userDAO;
        this.passwordService = passwordService;
    }

    public void userLogin(String login, String password) {
        validateLogin(login);
        validatePassword(login, password);
    }

    private void validatePassword(String login, String password) {
        String hashPassword = userDAO.getHashPasswordByLogin(login);
        if (!passwordService.checkPassword(password, hashPassword)) {
            throw new InvalidPasswordException("Неверный пароль");
        }
    }

    private void validateLogin(String login) {
        if (!userDAO.isUserExist(login)) {
            throw new LoginNotFoundException("Такого логина не существует");
        }
    }
}
