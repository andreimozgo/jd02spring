package by.academy.it.services.impl;

import by.academy.it.dao.exceptions.DaoException;
import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.User;
import by.academy.it.services.AbstractService;
import by.academy.it.services.UserService;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl extends AbstractService<User> implements UserService {
    private static UserServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(UserServiceImpl.class);
    private UserDaoImpl userDao = UserDaoImpl.getInstance();

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
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            passCheckResult = userDao.getPassword(enterLogin).equals(hash(enterPass));
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error check login: ", e);
        }
        return passCheckResult;
    }

    public void createOrUpdate(User user) {
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            String pass = user.getPassword();
            user.setPassword(hash(pass));
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
        Transaction transaction=null;
        try {
            transaction = util.getTransaction();
            user = userDao.getUserByLogin(login);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            LOG.error("Error get user: ", e);
        }
        return user;
    }

    public String hash(String input) {
        String md5Hashed = null;
        if (null == input) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5Hashed = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            LOG.error("Error in hash UserService: ", e);
        }
        return md5Hashed;
    }
}
