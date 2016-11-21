package by.academy.it.dao.impl;

import by.academy.it.dao.UserDao;
import by.academy.it.entity.User;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance = null;

    private UserDaoImpl() {
    }

    public static synchronized UserDaoImpl getInstance() {
        if (instance == null) instance = new UserDaoImpl();
        return instance;
    }

    public String getPassword(String login) {
        String hql = "SELECT U.password FROM User U WHERE U.login=:login";
        String pass = null;

        Session session = HibernateUtil.getInstance().getSession();
        Query query = session.createQuery(hql);
        query.setParameter("login", login);
        pass = (String) query.uniqueResult();
        return pass;
    }

    public User getUserByLogin(String login) {
        String hql = "SELECT U FROM User U WHERE U.login=:login";
        User user = null;

        Session session = HibernateUtil.getInstance().getSession();
        Query query = session.createQuery(hql);
        LOG.info("requested login: " + login);
        query.setParameter("login", login);
        user = (User) query.uniqueResult();
        return user;
    }
}

