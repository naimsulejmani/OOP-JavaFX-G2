package com.example.oopjavafxg2.infrastructures;

import java.sql.ResultSet;

@FunctionalInterface
public interface ToObjectAble<T> {
    T toObject(ResultSet resultSet);
}
