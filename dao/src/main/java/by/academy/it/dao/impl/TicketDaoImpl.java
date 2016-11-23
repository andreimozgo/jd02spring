package by.academy.it.dao.impl;

import by.academy.it.dao.TicketDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.List;

public class TicketDaoImpl extends BaseDao<Ticket> implements TicketDao {
    final Logger LOG = Logger.getLogger(TicketDaoImpl.class);
    private static TicketDaoImpl instance = null;

    private TicketDaoImpl() {
    }

    public static synchronized TicketDaoImpl getInstance() {
        if (instance == null) instance = new TicketDaoImpl();
        return instance;
    }

    public List getAllByUser(int userId) throws DaoException {
        String GET_ALL_TICKETS_BY_USER = "FROM Ticket T WHERE T.userId=:userId";
        List<Ticket> tickets;
        try {
            session = util.getSession();
            Query query = session.createQuery(GET_ALL_TICKETS_BY_USER);
            query.setParameter("userId", userId);
            tickets = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return tickets;
    }

    public List getAll() throws DaoException {
        String GET_ALL_TICKETS = "FROM Ticket";
        List<Ticket> tickets;
        try {
            session = util.getSession();
            Query query = session.createQuery(GET_ALL_TICKETS);
            tickets = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return tickets;
    }
}
