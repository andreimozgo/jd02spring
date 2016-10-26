package by.academy.it.dao;

import java.io.Serializable;

/**
 * Created by Андрей on 19.10.2016.
 */
public interface Dao<T> {
    void add(T t);

    void update(T t);

    T get(Class clazz, Serializable id);

    void delete(T t);

}
