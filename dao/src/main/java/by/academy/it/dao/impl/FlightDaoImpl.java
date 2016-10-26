package by.academy.it.dao.impl;

import by.academy.it.dao.entity.Entity;

import java.io.Serializable;
import java.sql.Connection;

/**
 * Created by Андрей on 19.10.2016.
 */
public class FlightDaoImpl implements FlightDao   {
    public static final String SELECT_ALL_FLIGHT = "SELECT * FROM flight";

    public FlightDaoImpl(Connection connection) {
            }



    public void add(Entity entity) {

    }

    public void update(Entity entity) {

    }

    public Entity get(Class clazz, Serializable id) {
        return null;
    }

    public void delete(Entity entity) {

    }
}
