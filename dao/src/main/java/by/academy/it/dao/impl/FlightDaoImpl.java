package by.academy.it.dao.impl;

import by.academy.it.dao.FlightDao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Flight;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.List;

public class FlightDaoImpl extends BaseDao<Flight> implements FlightDao {
    final Logger LOG = Logger.getLogger(FlightDaoImpl.class);
    private static FlightDaoImpl instance = null;

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
            String hql = "FROM Flight";
            Query query = session.createQuery(hql);
            flights = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return flights;
    }
}
