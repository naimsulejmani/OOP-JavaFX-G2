package com.example.oopjavafxg2.repositories;

import com.example.oopjavafxg2.infrastructures.CrudRepository;
import com.example.oopjavafxg2.infrastructures.DbHelper;
import com.example.oopjavafxg2.models.User;

import java.sql.*;
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
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public User toObject(ResultSet resultSet) {
        return null;
    }
}
