package by.academy.it.dao.impl;

import by.academy.it.dao.UserDao;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    final Logger LOG = Logger.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance = null;

    private UserDaoImpl() {
    }

    public static synchronized UserDaoImpl getInstance() {
        if (instance == null) instance = new UserDaoImpl();
        return instance;
    }

    public String getPassword(String login) {

        Connection connection = DataSource.getInstance().getConnection();
        Statement statement;
        String pass = null;

        try {
            statement = connection.createStatement();
            String query = "SELECT pass FROM users WHERE login=\"" + login + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            pass = result.getString(1);
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return pass;
    }

    public User getUserByLogin(String login) {

        Connection connection = DataSource.getInstance().getConnection();
        Statement statement;
        User user = new User();

        try {
            statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE login=\"" + login + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            int id = result.getInt(1);
            String role = result.getString(4);
            result.close();
            user.setUserId(id);
            user.setUserRole(role);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return user;
    }

    public void create(User entity) {

        String query = "INSERT INTO users (user_id, login, pass, role) " + "VALUES (?, ?, ?, ?)";
        Connection connection = DataSource.getInstance().getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setString(2, entity.getLogin());
            ps.setString(3, entity.getPassword());
            ps.setString(4, "user");
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
    }

    public User findEntityById(Integer id) {
        return null;
    }

    public void update(User entity) {
    }

    public void delete(Integer id) {
    }
}

