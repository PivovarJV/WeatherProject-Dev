package org.example.service;

import org.example.model.Session;
import org.example.model.User;

import java.util.UUID;

public interface SessionService {
    Session createSession(User user);
    boolean isValidSession(UUID sessionId);
    void deleteSession(UUID sessionId);
    User getUserBySession(UUID sessionId);
}
