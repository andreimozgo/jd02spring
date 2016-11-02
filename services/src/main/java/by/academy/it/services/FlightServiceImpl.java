package by.academy.it.services;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FlightServiceImpl implements IService<Flight> {
    private static FlightServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(FlightServiceImpl.class);

    public FlightServiceImpl() {
    }

    public static synchronized FlightServiceImpl getInstance() {
        if (instance == null) instance = new FlightServiceImpl();
        return instance;
    }

    public List<Flight> getAll() {
        Connection connection = DataSource.getInstance().getConnection();
        List<Flight> flights = null;
        try {
            connection.setAutoCommit(false);
            flights = FlightDaoImpl.getInstance().getAll();
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOG.error("Exception", e);
        }
        return flights;
    }

    public void create(Flight flight) {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            FlightDaoImpl.getInstance().create(flight);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOG.error("Exception", e);
        }
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
        }
        return flight;
    }

    public void update(Flight flight) {

    }
}
