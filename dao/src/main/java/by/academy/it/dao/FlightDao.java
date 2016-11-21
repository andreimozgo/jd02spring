package by.academy.it.dao;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;

import java.util.List;

public interface FlightDao extends Dao<Flight> {

    List<Flight> getAll() throws DaoException;
}