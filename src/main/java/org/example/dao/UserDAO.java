package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.exception.NotFoundException;
import org.example.model.Session;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public boolean isUserExist(String login) {
        String query = "SELECT COUNT(u) FROM User u WHERE u.login = :login";
        try {
            long count = entityManager.createQuery(query, Long.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    public String getHashPasswordByLogin(String login) {
        String query = "SELECT u.password FROM User u WHERE u.login = :login";
        try {
            return entityManager.createQuery(query, String.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("Такого логина не существует");
        }
    }

    public User getUserById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id))
                .orElseThrow(() -> new NotFoundException("Пользователя с " + id + " нет"));
    }

    public User getUserByLogin(String login) {
        String query = "SELECT u FROM User u WHERE u.login = :login";
        try {
            return entityManager.createQuery(query, User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException("Такого пользователя нет в базе данных");
        }
    }

    public List<Session> getListSessionByUser(User user) {
        String query = "FROM Session s WHERE s.user.id = :userId";
        return entityManager.createQuery(query, Session.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }
}
