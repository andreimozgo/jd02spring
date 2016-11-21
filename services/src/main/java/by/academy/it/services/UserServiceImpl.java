package by.academy.it.services;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.User;
import by.academy.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserServiceImpl implements IService<User> {
    private static UserServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    protected static HibernateUtil util = HibernateUtil.getInstance();

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
        Session session = util.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        passCheckResult = UserDaoImpl.getInstance().getPassword(enterLogin).equals(enterPass);
        transaction.commit();
        return passCheckResult;
    }

    public void create(User user) {
        Session session = util.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try {
            UserDaoImpl.getInstance().create(user);
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Exception", e);
        }
        transaction.commit();
    }

    public User findEntityById(Integer id) {
        return null;
    }

    public void update(User user) {

    }

    public void delete(Integer id) {

    }

    public User getUserByLogin(String login) {
        User user = null;
        Session session = util.getSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        user = UserDaoImpl.getInstance().getUserByLogin(login);
        transaction.commit();
        LOG.error("User got by login");
        return user;
    }
}
