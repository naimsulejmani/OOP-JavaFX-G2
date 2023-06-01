package com.example.oopjavafxg2.infrastructures;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ToObjectAble<T> {
    T toObject(ResultSet resultSet) throws SQLException;
}
