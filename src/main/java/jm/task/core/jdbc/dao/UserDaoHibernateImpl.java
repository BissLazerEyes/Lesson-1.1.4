package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory SessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = SessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String tableCreateQuery = "create table IF NOT EXISTS users(id BIGINT primary key auto_increment, name varchar(255), lastname varchar(255), age integer);";
        try {
            session.createSQLQuery(tableCreateQuery).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = SessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String tableDropUsers = "drop table IF EXISTS users;";
        try {
            session.createSQLQuery(tableDropUsers).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = SessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User users = new User();
            users.setName(name);
            users.setLastName(lastName);
            users.setAge(age);
            session.save(users);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = SessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String tableRemoveUsers = "DELETE FROM users WHERE ID = ?;";
        try {
            session.createSQLQuery(tableRemoveUsers).executeUpdate();
            session.remove(id);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = SessionFactory.openSession();
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = SessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String tableCleanUsers = "TRUNCATE TABLE users;";
        try {
            session.createSQLQuery(tableCleanUsers).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
}
