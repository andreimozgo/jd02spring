package by.academy.it.dao.impl;

import by.academy.it.dao.FlightDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;

import java.util.List;

public class FlightDaoImpl extends BaseDao<Flight> implements FlightDao {
    final Logger LOG = Logger.getLogger(FlightDaoImpl.class);
    private static FlightDaoImpl instance = null;
    private final String GET_ALL_FLIGHTS = "FROM Flight";

    private FlightDaoImpl() {
    }

    public static synchronized FlightDaoImpl getInstance() {
        if (instance == null) instance = new FlightDaoImpl();
        return instance;
    }

    public List getAll() throws DaoException {
        List<Flight> flights;
        try {
            session = util.getSession();
            Query query = session.createQuery(GET_ALL_FLIGHTS);
            flights = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return flights;
    }

    public List getAll(int recordsPerPage, int currentPage) throws DaoException {
        List<Flight> flights;
        try {
            session = util.getSession();
            Query query = session.createQuery(GET_ALL_FLIGHTS);
            query.setFirstResult((currentPage - 1) * recordsPerPage);
            query.setMaxResults(recordsPerPage);
            query.setCacheable(true);
            query.setCacheMode(CacheMode.NORMAL);
            flights = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return flights;
    }

    public Long getAmount() throws DaoException {
        Long amount;
        try {
            session = util.getSession();
            Criteria criteria = session.createCriteria(Flight.class);
            criteria.setProjection(Projections.rowCount());
            criteria.setCacheable(true);
            amount = (Long) criteria.uniqueResult();
            LOG.info("Amount of flights: " + amount);
        } catch (HibernateException e) {
            LOG.error("Unable to get number of flights. Error in DAO");
            throw new DaoException(e);
        }
        return amount;
    }
}
