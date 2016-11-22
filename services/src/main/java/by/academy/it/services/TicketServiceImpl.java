package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.entity.Ticket;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketServiceImpl implements Service<Ticket> {
    private static TicketServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(TicketServiceImpl.class);
    private TicketDaoImpl ticketDao = TicketDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getInstance();
    private Session session = null;
    private Transaction transaction = null;

    public TicketServiceImpl() {
    }

    public static synchronized TicketServiceImpl getInstance() {
        if (instance == null) instance = new TicketServiceImpl();
        return instance;
    }

    public List<Ticket> getAllByUser(int userId) {
        List<Ticket> tickets = null;
        session = util.getSession();
        transaction = session.beginTransaction();
        try {
            tickets = ticketDao.getAllByUser(userId);
            transaction.commit();
        } catch (DaoException e) {
            LOG.error("Error get all tickets: ", e);
            transaction.rollback();
        }
        return tickets;
    }

    public void createOrUpdate(Ticket ticket) {
        session = util.getSession();
        transaction = session.beginTransaction();
        try {
            ticketDao.create(ticket);
            transaction.commit();
        } catch (DaoException e) {
            LOG.error("Error create or update ticket: ", e);
            transaction.rollback();
        }
    }

    public Ticket findEntityById(Integer id) {
        Ticket ticket = null;
        session = util.getSession();
        try {
            transaction = session.beginTransaction();
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
        session = util.getSession();
        try {
            transaction = session.beginTransaction();
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
