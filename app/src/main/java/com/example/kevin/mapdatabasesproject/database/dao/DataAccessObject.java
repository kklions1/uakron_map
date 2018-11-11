package com.example.kevin.mapdatabasesproject.database.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interface to define basic functions in a DataAccessObject
 *
 * @param <T> generic type
 */
public interface DataAccessObject<T> {
    List<T> getAll();

    void save(T t);

    void update(T t);
}
