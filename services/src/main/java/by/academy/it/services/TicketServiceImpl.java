package by.academy.it.services;

import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Ticket;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TicketServiceImpl implements IService<Ticket> {
    private static TicketServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(TicketServiceImpl.class);

    public TicketServiceImpl() {
    }

    public static synchronized TicketServiceImpl getInstance() {
        if (instance == null) instance = new TicketServiceImpl();
        return instance;
    }

    public List<Ticket> getAllByUser(int userId) {
        Connection connection = DataSource.getInstance().getConnection();
        List<Ticket> ticket = null;
        try {
            connection.setAutoCommit(false);
            ticket = TicketDaoImpl.getInstance().getAllByUser(userId);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
        return ticket;
    }

    public void create(Ticket ticket) {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            TicketDaoImpl.getInstance().create(ticket);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
    }

    public Ticket findEntityById(Integer id) {
        Connection connection = DataSource.getInstance().getConnection();
        Ticket ticket = null;
        try {
            connection.setAutoCommit(false);
            ticket = TicketDaoImpl.getInstance().findEntityById(id);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
        return ticket;
    }

    public void update(Ticket ticket) {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            TicketDaoImpl.getInstance().update(ticket);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
    }

    public void delete(Integer id) {
    }

    public void payTicket(Integer ticketId) {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            Ticket ticket = TicketDaoImpl.getInstance().findEntityById(ticketId);
            ticket.setPaid(1);
            TicketServiceImpl.getInstance().update(ticket);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
    }
}
