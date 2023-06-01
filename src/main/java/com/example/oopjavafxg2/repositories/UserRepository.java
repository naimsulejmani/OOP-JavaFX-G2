package com.example.oopjavafxg2.repositories;

import com.example.oopjavafxg2.infrastructures.CrudRepository;
import com.example.oopjavafxg2.infrastructures.DbHelper;
import com.example.oopjavafxg2.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CrudRepository<User, Integer> {
    @Override
    public boolean add(User entity) {
        String query = """
                INSERT INTO dbo.Users (Name, Surname, Username, Password, Birthdate, Accepted)
                VALUES (?, ?, ?, ?, ?, ?);
                """;
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getPassword());
            statement.setDate(5, Date.valueOf(entity.getBirthdate()));
            statement.setBoolean(6, entity.isAccepted());

            return statement.executeUpdate() >= 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modify(Integer id, User entity) {
        String query = """
                UPDATE dbo.Users
                SET Name = ?,
                    Surname = ?,
                    Username = ?,
                    Password = ?,
                    Birthdate = ?,
                    Accepted = ?
                WHERE Id = ?
                """;
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getPassword());
            statement.setDate(5, Date.valueOf(entity.getBirthdate()));
            statement.setBoolean(6, entity.isAccepted());
            statement.setInt(7, id);
            return statement.executeUpdate() >= 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(Integer id) {
        String query = "DELETE FROM dbo.Users WHERE Id = ?";
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate() >= 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM dbo.Users";
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(toObject(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Integer id) {
        String query = "SELECT * FROM dbo.Users WHERE Id = ?";
        try (
                Connection connection = DbHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            User user = null;
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = toObject(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User toObject(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        String surname = resultSet.getString("Surname");
        String username = resultSet.getString("Username");
        String password = resultSet.getString("Password");
        LocalDate birthdate = resultSet.getDate("Birthdate").toLocalDate();
        boolean accepted = resultSet.getBoolean("Accepted");
        return new User(id, name, surname, birthdate, username, password, accepted);
    }
}












