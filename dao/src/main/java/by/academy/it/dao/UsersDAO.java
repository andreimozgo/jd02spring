package by.academy.it.dao;

import java.sql.*;
import java.util.List;

public class UsersDAO extends AbstractDAO<Integer, User> {

    public UsersDAO(Connection connection) {
        super(connection);
    }

    public String getPassword(String login) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pass;
    }

    public User getUserByLogin(String login) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean create(User entity) {

        String query = "INSERT INTO users (user_id, login, pass, role) " + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setString(2, entity.getLogin());
            ps.setString(3, entity.getPassword());
            ps.setString(4, "user");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List getAll() {
        return null;
    }

    @Override
    public User findEntityById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(User entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User update(User entity) {
        // TODO Auto-generated method stub
        return null;
    }
}

