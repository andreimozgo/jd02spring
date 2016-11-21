package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FlightServiceImpl implements IService<Flight> {
    private static FlightServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(FlightServiceImpl.class);
    protected static HibernateUtil util = HibernateUtil.getInstance();

    public FlightServiceImpl() {
    }

    public static synchronized FlightServiceImpl getInstance() {
        if (instance == null) instance = new FlightServiceImpl();
        return instance;
    }

    public List<Flight> getAll() {
        List<Flight> flights = null;
        Session session = util.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        flights = FlightDaoImpl.getInstance().getAll();
        transaction.commit();
        return flights;
    }

    public void create(Flight flight) {
        Session session = util.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            FlightDaoImpl.getInstance().create(flight);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Exception", e);
        }
        transaction.commit();
    }

    public void delete(Integer id) {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            FlightDaoImpl.getInstance().delete(id);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOG.error("Exception", e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public Flight findEntityById(Integer id) {
        Connection connection = DataSource.getInstance().getConnection();
        Flight flight = null;
        try {
            connection.setAutoCommit(false);
            flight = FlightDaoImpl.getInstance().findEntityById(id);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOG.error("Exception", e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public void update(Flight flight) {

    }
}
