package by.academy.it.services;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.entity.Flight;

import java.util.List;

public class FlightServiceImpl implements IService<Flight> {
    private static FlightServiceImpl instance = null;

    public FlightServiceImpl() {
    }

    public static synchronized FlightServiceImpl getInstance() {
        if (instance == null) instance = new FlightServiceImpl();
        return instance;
    }

    public List<Flight> getAll() {
        return FlightDaoImpl.getInstance().getAll();
    }

    public void create(Flight flight) {
        FlightDaoImpl.getInstance().create(flight);
    }

    public void delete(Integer id) {
        FlightDaoImpl.getInstance().delete(id);
    }

    public Flight findEntityById(Integer id) {
        return FlightDaoImpl.getInstance().findEntityById(id);
    }

    public void update(Flight flight) {

    }
}
