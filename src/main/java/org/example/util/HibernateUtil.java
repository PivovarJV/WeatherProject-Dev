package org.example.util;

import lombok.Getter;
import org.example.model.Location;
import org.example.model.Session;
import org.example.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Location.class);
            configuration.addAnnotatedClass(Session.class);
            configuration.addAnnotatedClass(User.class);
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
