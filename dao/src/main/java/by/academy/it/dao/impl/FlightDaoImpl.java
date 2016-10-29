package by.academy.it.dao.impl;

import by.academy.it.dao.FlightDao;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements FlightDao {
    final Logger LOG = Logger.getLogger(FlightDaoImpl.class);
    private static FlightDaoImpl instance = null;
    public static final String SELECT_ALL_FLIGHT = "SELECT * FROM flight";


    public FlightDaoImpl() {
    }

    public static synchronized FlightDaoImpl getInstance() {
        if (instance == null) instance = new FlightDaoImpl();
        return instance;
    }

    public List<Flight> getAll() {
        List<Flight> lst = new ArrayList<Flight>();
        Connection connection = DataSource.getInstance().getConnection();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(SELECT_ALL_FLIGHT);
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Flight flight = new Flight(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                            rs.getByte(5));
                    lst.add(flight);
                }
                rs.close();
            } catch (SQLException e) {
                LOG.error("Exception: ", e);
            } finally {
                ps.close();
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return lst;
    }

    public void create(Flight entity) {
        Connection connection = DataSource.getInstance().getConnection();
        Date sqlDate = Date.valueOf(entity.getDate());
        try {
            String query = "INSERT INTO flight (flight_id, date, seats, cost, up_cost) " + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setDate(2, sqlDate);
            ps.setInt(3, entity.getSeats());
            ps.setInt(4, entity.getCost());
            ps.setInt(5, entity.getUpCost());
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
    }

    public Flight findEntityById(Integer id) {
        Connection connection = DataSource.getInstance().getConnection();
        Statement statement;
        Flight flight = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM flight WHERE flight_id=\"" + id + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            flight = new Flight(id, result.getString(2), result.getInt(3), result.getInt(4), result.getByte(5));
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return flight;
    }

    public void update(Flight entity) {
    }

    public void delete(Integer id) {
        Connection connection = DataSource.getInstance().getConnection();
        String query = "DELETE FROM flight WHERE flight_id =" + id;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
    }
}
