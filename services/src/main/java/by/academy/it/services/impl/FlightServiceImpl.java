package by.academy.it.services.impl;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.entity.Flight;
import by.academy.it.services.AbstractService;
import by.academy.it.services.FlightService;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.util.List;

public class FlightServiceImpl extends AbstractService<Flight> implements FlightService {
    private static FlightServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(FlightServiceImpl.class);
    private FlightDaoImpl flightDao = FlightDaoImpl.getInstance();

    public FlightServiceImpl() {
    }

    public static synchronized FlightServiceImpl getInstance() {
        if (instance == null) instance = new FlightServiceImpl();
        return instance;
    }

    public List<Flight> getAll() {
        List<Flight> flights = null;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            flights = flightDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error get all flights: ", e);
        }
        return flights;
    }

    public List<Flight> getAll(int recordsPerPage, int currentPage) {
        List<Flight> flights = null;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            flights = flightDao.getAll(recordsPerPage, currentPage);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error get all flights: ", e);
        }
        return flights;
    }

    public List<Flight> getAll(int recordsPerPage, int currentPage, String flightDate) {
        List<Flight> flights = null;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            flights = flightDao.getAll(recordsPerPage, currentPage, flightDate);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error get all flights: ", e);
        }
        return flights;
    }

    public void createOrUpdate(Flight flight) {
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            flightDao.create(flight);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error create or update flight: ", e);
        }
        transaction.commit();
    }

    public void delete(Integer id) {
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            flightDao.delete(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error delete flight: ", e);
        }
    }

    public Flight findEntityById(Integer id) {
        Flight flight = null;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            flight = flightDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error find flight: ", e);
        }
        return flight;
    }

    public int getNumberOfPages(int recordsPerPage) {
        int numberOfPages = 1;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            Long numberOfRecords = flightDao.getAmount();
            numberOfPages = Math.round(numberOfRecords / recordsPerPage);
            if ((numberOfRecords % recordsPerPage) > 0) numberOfPages++;
            transaction.commit();
            LOG.info("Count of flight pages: " + numberOfPages);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error getting count of flight pages", e);
        }
        return numberOfPages;
    }

    public int getNumberOfPages(int recordsPerPage, String flightDate) {
        int numberOfPages = 1;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            Long numberOfRecords = flightDao.getAmount(flightDate);
            numberOfPages = Math.round(numberOfRecords / recordsPerPage);
            if ((numberOfRecords % recordsPerPage) > 0) numberOfPages++;
            transaction.commit();
            LOG.info("Count of flight pages: " + numberOfPages);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error getting count of flight pages", e);
        }
        return numberOfPages;
    }
}

