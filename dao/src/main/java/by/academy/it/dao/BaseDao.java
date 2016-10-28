package by.academy.it.dao;

import by.academy.it.entity.Entity;

public interface BaseDao<T extends Entity> {
    void create(T entity);

    T findEntityById(Integer id);

    void update(T entity);

    void delete(Integer id);

}
