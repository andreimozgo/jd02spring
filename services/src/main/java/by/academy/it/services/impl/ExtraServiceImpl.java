package by.academy.it.services.impl;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.Extra;
import by.academy.it.services.AbstractService;
import by.academy.it.services.ExtraService;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

public class ExtraServiceImpl extends AbstractService<Extra> implements ExtraService {
    private static ExtraServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(ExtraServiceImpl.class);

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
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
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
