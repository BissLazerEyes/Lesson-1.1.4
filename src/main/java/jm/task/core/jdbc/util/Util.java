package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    static String HOSTNAME = "localhost";
    static String DBNAME = "users";
    static String USERNAME = "root";
    static String PASSWORD = "Lkj098hgf765";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        String connectionURL = "jdbc:mysql://" + HOSTNAME + ":3306/" + DBNAME;
        Connection conn = DriverManager.getConnection(connectionURL, USERNAME, PASSWORD);
        return conn;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("connection.driver_class", "com.mysql.jdbc.Driver")
                        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users")
                        .setProperty("hibernate.connection.username", "root")
                        .setProperty("hibernate.connection.password", "Lkj098hgf765")
                        .addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
