package by.academy.it.services;

import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements IService<User> {
    private static UserServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) instance = new UserServiceImpl();
        return instance;
    }

    public boolean checkLogin(String enterLogin, String enterPass) {
        boolean passCheckResult = false;
        Connection connection = DataSource.getInstance().getConnection();
        if (enterLogin.equals("") || enterPass.equals("")) {
            return passCheckResult;
        }
        try {
            connection.setAutoCommit(false);
            passCheckResult = UserDaoImpl.getInstance().getPassword(enterLogin).equals(enterPass);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
        return passCheckResult;
    }

    public void create(User user) {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            UserDaoImpl.getInstance().create(user);
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }
    }

    public User findEntityById(Integer id) {
        return null;
    }

    public void update(User user) {

    }

    public void delete(Integer id) {

    }

    public User getUserByLogin(String login) {
        Connection connection = DataSource.getInstance().getConnection();
        User user = null;
        try {
            connection.setAutoCommit(false);
            user = UserDaoImpl.getInstance().getUserByLogin(login);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOG.error("Exception", e);
        }
        return user;
    }
}
