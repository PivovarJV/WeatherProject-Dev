package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.model.Session;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SessionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void createSession(Session session) {
       entityManager.persist(session);
    }

    public void deleteSession(UUID uuid) {
        entityManager.remove(getSessionByUUID(uuid));
    }

    public Session getSessionByUUID(UUID uuid) {
        return entityManager.find(Session.class, uuid);
    }
}
