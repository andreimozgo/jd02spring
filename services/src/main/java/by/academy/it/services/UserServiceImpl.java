package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.User;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements Service<User> {
    private static UserServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private HibernateUtil util = HibernateUtil.getInstance();
    private Session session = null;
    private Transaction transaction = null;

    public UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) instance = new UserServiceImpl();
        return instance;
    }

    public boolean checkLogin(String enterLogin, String enterPass) {
        boolean passCheckResult = false;
        if (enterLogin.equals("") || enterPass.equals("")) {
            return passCheckResult;
        }
        session = util.getSession();
        transaction = session.beginTransaction();
        try {
            passCheckResult = userDao.getPassword(enterLogin).equals(enterPass);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error check login: ", e);
        }
        return passCheckResult;
    }

    public void createOrUpdate(User user) {
        session = util.getSession();
        transaction = session.beginTransaction();
        try {
            userDao.create(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error create or update user: ", e);
        }
    }

    public User findEntityById(Integer id) {
        return null;
    }

    public void delete(Integer id) {

    }

    public User getUserByLogin(String login) {
        User user = null;
        session = util.getSession();
        transaction = session.beginTransaction();
        try {
            user = userDao.getUserByLogin(login);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error get user: ", e);
        }
        return user;
    }
}
