package com.zhaksylyk.dao;

import com.zhaksylyk.model.User;
import com.zhaksylyk.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String FIND_ALL_USERS = """
            SELECT id, name, lastname, age
            from users;
            """;
    private static final String DELETE_SQL = """
            DELETE FROM users
            WHERE id=?
            """;
    private static final String SAVE_USER = """
            INSERT INTO users(name, lastname, age)
            VALUES(?, ?, ?);
            """;
    private static final String CREATE_USERS_TABLE = """
            CREATE TABLE IF NOT EXISTS users (
            id SERIAL,
            name VARCHAR(255),
            lastname VARCHAR(255),
            age INT
            );
            """;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = ConnectionManager.get();
             var statement = connection.createStatement()) {
            statement.execute(CREATE_USERS_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = ConnectionManager.get();
             var statement = connection.createStatement()) {
            String DROP_USERS_TABLE = "DROP TABLE IF EXISTS USERS";
            statement.execute(DROP_USERS_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_USER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = ConnectionManager.get();
             var prepareStatement= connection.prepareStatement(DELETE_SQL)) {
            prepareStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement prepareStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = prepareStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age")
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = ConnectionManager.get();
             var statement = connection.createStatement()) {
            String TRUNCATE_USERS_TABLE = "TRUNCATE TABLE USERS";
            statement.execute(TRUNCATE_USERS_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
