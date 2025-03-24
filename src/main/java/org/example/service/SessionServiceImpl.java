package org.example.service;

import org.example.dao.SessionDAO;
import org.example.dao.UserDAO;
import org.example.model.Session;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService{

    private final SessionDAO sessionDAO;
    private final UserDAO userDAO;

    @Autowired
    public SessionServiceImpl(SessionDAO sessionDAO, UserDAO userDAO) {
        this.sessionDAO = sessionDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Session createSession(User user) {
        Session session = new Session(user);
        sessionDAO.createSession(session);
        return session;
    }

    @Override
    public boolean isValidSession(UUID sessionId) {
        return false;
    }

    @Override
    public void deleteSession(UUID sessionId) {
        sessionDAO.deleteSession(sessionId);
    }

    @Override
    public User getUserBySession(UUID sessionId) {
        return userDAO.getUserById(sessionDAO.getSessionByUUID(sessionId).getUser().getId());
    }
}
