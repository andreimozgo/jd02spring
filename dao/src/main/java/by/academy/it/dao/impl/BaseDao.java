package by.academy.it.dao.impl;

import by.academy.it.dao.Dao;
import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.entity.AbstractEntity;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.ParameterizedType;

public class BaseDao<T extends AbstractEntity> implements Dao<T> {
    protected static HibernateUtil util = HibernateUtil.getInstance();
    private static Logger log = Logger.getLogger(BaseDao.class);
    private Transaction transaction = null;

    public void create(T t) throws DaoException {
        try {
            Session session = util.getSession();
            session.saveOrUpdate(t);
            log.info("create:" + t);
            //session.flush();
        } catch (HibernateException e) {
            log.error("Error create AbstractEntity in Dao" + e);
            throw new DaoException(e);
        }
    }

    public T findEntityById(Integer id) throws DaoException {
        log.info("Get class by id:" + id);
        T t = null;
        try {
            Session session = util.getSession();
            transaction = session.beginTransaction();
            t = (T) session.get(getPersistentClass(), id);
            transaction.commit();
            log.info("get clazz:" + t);
        } catch (HibernateException e) {
            transaction.rollback();
            log.error("Error get " + getPersistentClass() + " in Dao" + e);
            throw new DaoException(e);
        }
        return t;
    }

    public void delete(Integer id) throws DaoException {
        try {
            Session session = util.getSession();
            transaction = session.beginTransaction();
            T t = (T) session.get(getPersistentClass(), id);
            session.delete(t);
            transaction.commit();
            log.info("Delete:" + t);
        } catch (HibernateException e) {
            log.error("Error delete AbstractEntity in Dao" + e);
            transaction.rollback();
            throw new DaoException(e);
        }

    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
