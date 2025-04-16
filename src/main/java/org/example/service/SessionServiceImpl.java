package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dao.SessionDAO;
import org.example.dao.UserDAO;
import org.example.model.Session;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService{

    private final SessionDAO sessionDAO;
    private final UserDAO userDAO;

    @Transactional
    @Override
    public Session createSession(User user) {
        Session session = new Session(user);
        sessionDAO.createSession(session);
        return session;
    }

    @Override
    public boolean isValidSession(UUID sessionId) {
        Session session = sessionDAO.getSessionByUUID(sessionId);
        return session.getExpiresAt().isAfter(LocalDateTime.now());
    }

    @Transactional
    @Override
    public void deleteSession(UUID sessionId) {
        sessionDAO.deleteSession(sessionId);
    }

    @Override
    public User getUserBySession(UUID sessionId) {
        return userDAO.getUserById(sessionDAO.getSessionByUUID(sessionId).getUser().getId());
    }
}
