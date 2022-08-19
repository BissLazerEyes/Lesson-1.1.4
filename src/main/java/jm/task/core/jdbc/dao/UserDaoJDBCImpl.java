package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException {

    }

    @Override
    public void createUsersTable() {
        String tableCreateQuery = "CREATE TABLE IF NOT EXISTS users (id long, name char(255), lastName char(255), age integer);";
        try (Statement statement = connection.createStatement()) {
            statement.execute(tableCreateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String tableDropUsers = "drop table IF EXISTS users;";
        try (Statement statement = connection.createStatement()) {
            statement.execute(tableDropUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String tableSaveUsers = "INSERT INTO users (name,lastname,age) VALUES (?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(tableSaveUsers)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String tableRemoveUsers = "DELETE FROM users WHERE ID = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(tableRemoveUsers)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String tableAllUsers = "SELECT name,lastname,age from users";

        try (Statement statement = connection.prepareStatement(tableAllUsers)) {
            ResultSet resultSet = statement.executeQuery(tableAllUsers);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String tableCleanUsers = "TRUNCATE TABLE users;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(tableCleanUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}