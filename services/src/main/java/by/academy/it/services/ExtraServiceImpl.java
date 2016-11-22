package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.ExtraDaoImpl;
import by.academy.it.entity.Extra;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExtraServiceImpl implements Service<Extra> {
    private static ExtraServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(ExtraServiceImpl.class);
    private ExtraDaoImpl optionDao = ExtraDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getInstance();
    private Session session = null;
    private Transaction transaction = null;

    public ExtraServiceImpl() {
    }

    public static synchronized ExtraServiceImpl getInstance() {
        if (instance == null) instance = new ExtraServiceImpl();
        return instance;
    }

    public void createOrUpdate(Extra extra) {

    }

    public Extra findEntityById(Integer id) {
        Extra extra = null;
        session = util.getSession();
        try {
            transaction = session.beginTransaction();
            extra = optionDao.findEntityById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error find Extra: ", e);
        }
        return extra;
    }

    public void delete(Integer id) {

    }
}
