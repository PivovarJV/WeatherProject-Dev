package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.example.exception.LoginNotFoundException;
import org.example.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public boolean isUserExist(String login) {
        String jpql = "SELECT COUNT(u) FROM User u WHERE u.login = :login";
        try {
            Long count = entityManager.createQuery(jpql, Long.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    public String getHashPasswordByLogin(String login) {
        String jpql = "SELECT u.password FROM User u WHERE u.login = :login";
        try {
            return entityManager.createQuery(jpql, String.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new LoginNotFoundException("Такого логина не существует");
        }
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
}
