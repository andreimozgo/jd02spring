package by.academy.it.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ServicesDAO extends AbstractDAO<Integer, Services> {

    public ServicesDAO(Connection connection) {
        super(connection);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Services findEntityById(Integer id) {
        Statement statement;
        Services service = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT name, cost FROM services WHERE services_id=\"" + id + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            service = new Services(result.getString(1), result.getInt(2));
            result.close();
            statement.close();
            return service;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(Services entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean create(Services entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Services update(Services entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Services> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
