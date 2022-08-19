package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoHibernateImpl();

    public UserServiceImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() {
        dao.createUsersTable();
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() {
        dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);
        System.out.println("Пользователь сохранён: " + "\"" + name + " " + lastName + "\"");
    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
        System.out.println("Пользователь удалён");
    }

    public List<User> getAllUsers() {
        List<User> allUsers = dao.getAllUsers();
        for (User users : allUsers) {
            System.out.println("Все пользователи: " + users);
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();

    }
}