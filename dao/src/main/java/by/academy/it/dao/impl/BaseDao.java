package by.academy.it.dao.impl;

import by.academy.it.dao.Dao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.AbstractEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao<T extends AbstractEntity> implements Dao<T> {
    private static Logger log = Logger.getLogger(BaseDao.class);
    protected Query query;
    private SessionFactory sessionFactory;

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    private Class pClass;

    public void create(T t) throws DaoException {
        try {
            getSession().saveOrUpdate(t);
            log.info("Created or updated: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    public T findEntityById(Integer id) throws DaoException {
        T t;
        try {
            log.info("Get Entity by id:" + id);
            t = (T) getSession().get(pClass, id);
            log.info("Got entity: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return t;
    }

    public void delete(Integer id) throws DaoException {
        try {
            T t = (T) getSession().get(pClass, id);
            getSession().delete(t);
            log.info("Deleted: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }
}
