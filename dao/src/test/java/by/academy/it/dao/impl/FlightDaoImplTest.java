package by.academy.it.dao.impl;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FlightDaoImplTest extends Assert {
    @Autowired
    FlightDaoImpl flightDao;
    final Logger LOG = Logger.getLogger(ExtraDaoImpl.class);

    @Test
    public void testCreate() {
        try {
            Flight flight = new Flight(1, "2016-01-01", 20, 10, (byte) 1);
            flightDao.create(flight);
            Flight readFlight = flightDao.findEntityById(1);
            assertNotNull(readFlight);
            assertEquals(flight, readFlight);
            flightDao.delete(1);
        } catch (DaoException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testDelete(){
        try {
            Flight flight = new Flight(2, "2016-01-01", 20, 10, (byte) 1);
            flightDao.create(flight);
            flightDao.delete(2);
            Flight deletedFlight = flightDao.findEntityById(2);
            assertNull(deletedFlight);
        } catch (DaoException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testFindEntityById(){
        try {
            Flight flight = new Flight(3, "2016-11-22", 30, 10, (byte) 0);
            flightDao.create(flight);
            Flight readflight = flightDao.findEntityById(3);
            assertNotNull(readflight);
            assertEquals(flight, readflight);
            flightDao.delete(3);
        } catch (DaoException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testGetAmount(){
        try {
            long amount = flightDao.getAmount();
            assertEquals(0, amount);
        } catch (DaoException e) {
            LOG.error(e);
        }
    }

    @Test
    public void testGetAll(){
        try {
            List<Flight> flights = flightDao.getAll();
            assertEquals(0, flights.size());
        } catch (DaoException e) {
            LOG.error(e);
        }
    }
}