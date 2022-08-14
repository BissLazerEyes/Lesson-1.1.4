package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    private final static UserService userService;

    static {
        try {
            userService = new UserServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        userService.createUsersTable();

        userService.saveUser("Гусев", "Иван", (byte) 32);
        userService.saveUser("Зайцев", "Максим", (byte) 45);
        userService.saveUser("Гусева", "Светлана", (byte) 24);
        userService.saveUser("Зайцев", "Захар", (byte) 56);
        userService.saveUser("Лапшина", "Алина", (byte) 16);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}