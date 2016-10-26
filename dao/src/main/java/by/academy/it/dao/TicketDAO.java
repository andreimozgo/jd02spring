package by.academy.it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO extends AbstractDAO<Integer, Ticket> {

    public TicketDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Ticket findEntityById(Integer id) {

        Statement statement;
        Ticket ticket = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM ticket WHERE ticket_id=\"" + id + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            ticket = new Ticket((int) id, result.getInt(2), result.getInt(3), result.getInt(5), result.getByte(4));
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Ticket entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean create(Ticket entity) {
        String query = "INSERT INTO ticket (ticket_id, flight_id, client_id, has_paid, cost) " + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setInt(2, entity.getFligthId());
            ps.setInt(3, entity.getUserId());
            ps.setInt(4, entity.getPaid());
            ps.setInt(5, entity.getCost());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Ticket update(Ticket entity) {

        Ticket ticket = null;
        String query = "UPDATE ticket SET cost=" + entity.getCost() + ", has_paid=" + entity.getPaid() + " WHERE ticket_id=" + entity.getId();

        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return ticket;
        }
        return findEntityById(entity.getId());
    }

    @Override
    public List<Ticket> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Ticket> getAllByUser(int userId) {

        List<Ticket> lst = new ArrayList<Ticket>();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT * FROM ticket WHERE client_id=\"" + userId + "\"");
            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Ticket ticket = new Ticket(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(5),
                            rs.getByte(4));
                    lst.add(ticket);
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
}
