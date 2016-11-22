package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.entity.Flight;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FlightServiceImpl implements Service<Flight> {
    private static FlightServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(FlightServiceImpl.class);
    private FlightDaoImpl flightDao = FlightDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getInstance();
    private Session session;
    private Transaction transaction;

    public FlightServiceImpl() {
    }

    public static synchronized FlightServiceImpl getInstance() {
        if (instance == null) instance = new FlightServiceImpl();
        return instance;
    }

    public List<Flight> getAll() {
        List<Flight> flights = null;
        session = util.getSession();
        try {
            transaction = session.beginTransaction();
            flights = flightDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error get all flights: ", e);
        }
        return flights;
    }

    public void createOrUpdate(Flight flight) {
        session = util.getSession();
        try {
            transaction = session.beginTransaction();
            flightDao.create(flight);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error create or update flight: ", e);
        }
        transaction.commit();
    }

    public void delete(Integer id) {
        session = util.getSession();
        try {
            transaction = session.beginTransaction();
            flightDao.delete(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error delete flight: ", e);
        }
    }

    public Flight findEntityById(Integer id) {
        Flight flight = null;
        session = util.getSession();
        try {
            transaction = session.beginTransaction();
            flight = flightDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error find flight: ", e);
        }
        return flight;
    }
}

