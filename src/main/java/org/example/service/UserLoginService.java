package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDAO;
import org.example.exception.InvalidPasswordException;
import org.example.exception.NotFoundException;
import org.example.model.Session;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.util.PasswordService.isPasswordValid;

@RequiredArgsConstructor
@Service
public class UserLoginService {

    private final UserDAO userDAO;

    public void userLogin(String login, String password) {
        validateLogin(login);
        validatePassword(login, password);
    }

    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    public List<Session> getSessionByUser(User user) {
        return userDAO.getListSessionByUser(user);
    }

    private void validatePassword(String login, String password) {
        String hashPassword = userDAO.getHashPasswordByLogin(login);
        if (!isPasswordValid(password, hashPassword)) {
            throw new InvalidPasswordException("Неверный пароль");
        }
    }

    private void validateLogin(String login) {
        if (!userDAO.isUserExist(login)) {
            throw new NotFoundException("Такого логина не существует");
        }
    }
}