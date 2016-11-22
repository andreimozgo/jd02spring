package by.academy.it.dao.impl;

import by.academy.it.dao.Dao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.AbstractEntity;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;

public class BaseDao<T extends AbstractEntity> implements Dao<T> {
    protected static HibernateUtil util = HibernateUtil.getInstance();
    private static Logger log = Logger.getLogger(BaseDao.class);
    protected Session session;
    protected Query query;

    public void create(T t) throws DaoException {
        try {
            session = util.getSession();
            session.saveOrUpdate(t);
            log.info("Created or updated: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    public T findEntityById(Integer id) throws DaoException {
        T t;
        try {
            log.info("Get Entity by id:" + id);
            session = util.getSession();
            t = (T) session.get(getPersistentClass(), id);
            log.info("Got entity: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return t;
    }

    public void delete(Integer id) throws DaoException {
        T t;
        try {
            session = util.getSession();
            t = (T) session.get(getPersistentClass(), id);
            session.delete(t);
            log.info("Deleted: " + t);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
