package by.academy.it.dao.impl;

import by.academy.it.dao.Entity.Entity;
import by.academy.it.dao.Entity.Flight;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by Андрей on 19.10.2016.
 */
public class FlightDaoImpl implements FlightDao   {
    public static final String SELECT_ALL_FLIGHT = "SELECT * FROM flight";

    public FlightDaoImpl(Connection connection) {
        super(connection);
    }

    public List<Flight> getAll() {

        List<Flight> lst = new ArrayList<>();
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
                e.printStackTrace();
            } finally {
                ps.close();
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return lst;

    }

    public void add(Entity entity) {

    }

    public void update(Entity entity) {

    }

    public Entity get(Class clazz, Serializable id) {
        return null;
    }

    public void delete(Entity entity) {

    }
}
