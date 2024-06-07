package com.zhaksylyk.dao;

import com.zhaksylyk.model.User;
import com.zhaksylyk.util.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createSQLQuery("""
                CREATE TABLE IF NOT EXISTS users(
                    id SERIAL,
                    name VARCHAR(255),
                    lastname VARCHAR(255),
                    age INT
                );""").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()){
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//            query.from(User.class);
//            return session.createQuery(query).getResultList();

            users = session.createQuery("SELECT user FROM User user", User.class).getResultList();
            session.getTransaction().commit();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
