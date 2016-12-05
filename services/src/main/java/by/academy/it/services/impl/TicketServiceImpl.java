package by.academy.it.services.impl;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.entity.Ticket;
import by.academy.it.services.AbstractService;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.util.List;

public class TicketServiceImpl extends AbstractService<Ticket> implements TicketService {
    private static TicketServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(TicketServiceImpl.class);
    private TicketDaoImpl ticketDao = TicketDaoImpl.getInstance();

    public TicketServiceImpl() {
    }

    public static synchronized TicketServiceImpl getInstance() {
        if (instance == null) instance = new TicketServiceImpl();
        return instance;
    }

    public List<Ticket> getAllByUser(int userId) {
        List<Ticket> tickets = null;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            tickets = ticketDao.getAllByUser(userId);
            transaction.commit();
        } catch (DaoException e) {
            LOG.error("Error get all tickets: ", e);
            transaction.rollback();
        }
        return tickets;
    }

    public List<Ticket> getAll() {
        List<Ticket> tickets = null;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            tickets = ticketDao.getAll();
            transaction.commit();
        } catch (DaoException e) {
            LOG.error("Error get all tickets: ", e);
            transaction.rollback();
        }
        return tickets;
    }

    public void createOrUpdate(Ticket ticket) {
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            ticketDao.create(ticket);
            transaction.commit();
        } catch (DaoException e) {
            LOG.error("Error create or update ticket: ", e);
            transaction.rollback();
        }
    }

    public Ticket findEntityById(Integer id) {
        Ticket ticket = null;
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            ticket = ticketDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error find ticket: ", e);
        }
        return ticket;
    }

    public void delete(Integer id) {
    }

    public void payTicket(Integer ticketId) {
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            Ticket ticket = ticketDao.findEntityById(ticketId);
            ticket.setPaid(1);
            ticketDao.create(ticket);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error pay ticket: ", e);
        }
    }
}
