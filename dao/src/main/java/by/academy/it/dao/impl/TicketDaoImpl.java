package by.academy.it.dao.impl;

import by.academy.it.dao.TicketDao;
import by.academy.it.entity.Ticket;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    final Logger LOG = Logger.getLogger(TicketDaoImpl.class);
    Connection connection;

    public TicketDaoImpl(Connection connection) {

        this.connection = connection;
    }

    public Ticket findEntityById(Integer id) {

        Statement statement;
        Ticket ticket = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM ticket WHERE ticket_id=\"" + id + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            ticket = new Ticket(id, result.getInt(2), result.getInt(3), result.getInt(5), result.getByte(4));
            result.close();
            statement.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return ticket;
    }

    public void create(Ticket entity) {
        String query = "INSERT INTO ticket (ticket_id, flight_id, client_id, has_paid, cost) " + "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setInt(2, entity.getFligthId());
            ps.setInt(3, entity.getUserId());
            ps.setInt(4, entity.getPaid());
            ps.setInt(5, entity.getCost());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
    }

    public void update(Ticket entity) {

        String query = "UPDATE ticket SET cost=" + entity.getCost() + ", has_paid=" + entity.getPaid() + " WHERE ticket_id=" + entity.getId();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
    }

    public void delete(Integer id) {
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
                LOG.error("Exception: ", e);
            } finally {
                ps.close();
            }
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return lst;
    }
}
