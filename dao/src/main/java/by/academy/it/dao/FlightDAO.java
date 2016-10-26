package by.academy.it.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO extends AbstractDAO<Integer, Flight> {
    public static final String SELECT_ALL_FLIGHT = "SELECT * FROM flight";

    public FlightDAO(Connection connection) {
        super(connection);
    }

    public List<Flight> getAll() {

        List<Flight> lst = new ArrayList<Flight>();
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

    @Override
    public boolean create(Flight entity) {

        Date sqlDate = Date.valueOf(entity.getDate());
        try {
            String query = "INSERT INTO flight (flight_id, date, seats, cost, up_cost) " + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setDate(2, sqlDate);
            ps.setInt(3, entity.getSeats());
            ps.setInt(4, entity.getCost());
            ps.setInt(5, entity.getUpCost());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Flight findEntityById(Integer id) {
        Statement statement;
        Flight flight = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM flight WHERE flight_id=\"" + id + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            flight = new Flight((int) id, result.getString(2), result.getInt(3), result.getInt(4), result.getByte(5));
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public boolean delete(Integer id) {
        String query = "DELETE FROM flight WHERE flight_id =" + id;
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Flight entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Flight update(Flight entity) {
        // TODO Auto-generated method stub
        return null;
    }
}
