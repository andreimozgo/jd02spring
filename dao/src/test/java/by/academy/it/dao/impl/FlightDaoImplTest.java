package by.academy.it.dao.impl;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FlightDaoImplTest extends Assert {
    FlightDaoImpl flightDao = FlightDaoImpl.getInstance();

    @Test
    public void testCreate() throws DaoException {
        Flight flight = new Flight(15, "2016-01-01", 20, 10, (byte) 1);
        flightDao.create(flight);
        Flight readFlight = flightDao.findEntityById(15);
        assertNotNull(readFlight);
        assertEquals(flight, readFlight);
        flightDao.delete(15);
    }

    @Test
    public void testDelete() throws DaoException {
        Flight flight = new Flight(16, "2016-01-01", 20, 10, (byte) 1);
        flightDao.create(flight);
        flightDao.delete(16);
        Flight deletedFlight = null;
        deletedFlight = flightDao.findEntityById(16);
        assertNull(deletedFlight);
    }

    @Test
    public void testFindEntityById() throws DaoException {
        Flight flight = new Flight(1, "2016-11-22", 30, 10, (byte) 0);
        Flight readflight = flightDao.findEntityById(1);
        assertNotNull(readflight);
        assertEquals(flight, readflight);
    }

    @Test
    public void testGetAmount() throws DaoException {
        long amount = flightDao.getAmount();
        assertNotNull(amount);
        assertEquals(14, amount);
    }

    @Test
    public void testGetAll() throws DaoException {
        Flight flight = new Flight(1, "2016-11-22", 30, 10, (byte) 0);
        List<Flight> flights = flightDao.getAll(1, 1);
        Flight readFlight = flights.get(0);
        assertNotNull(readFlight);
        assertEquals(flight, readFlight);
    }

}