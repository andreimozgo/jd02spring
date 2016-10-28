package by.academy.it.dao;

import by.academy.it.entity.Flight;

import java.util.List;

public interface FlightDao extends BaseDao<Flight> {

    Flight findEntityById(Integer id);

    List<Flight> getAll();
}