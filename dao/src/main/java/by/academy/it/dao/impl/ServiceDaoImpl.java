package by.academy.it.dao.impl;

import by.academy.it.dao.ServiceDao;
import by.academy.it.entity.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDaoImpl implements ServiceDao {
    final Logger LOG = Logger.getLogger(ServiceDaoImpl.class);
    Connection connection;

    public ServiceDaoImpl(Connection connection) {

        this.connection = connection;
    }

    public void create(Service entity) {
    }

    public Service findEntityById(Integer id) {
        Statement statement;
        Service service = null;
        try {
            statement = connection.createStatement();
            String query = "SELECT name, cost FROM services WHERE services_id=\"" + id + "\"";
            ResultSet result = statement.executeQuery(query);
            result.next();
            service = new Service(result.getString(1), result.getInt(2));
            result.close();
            statement.close();
            return service;
        } catch (SQLException e) {
            LOG.error("Exception: ", e);
        }
        return service;
    }

    public void update(Service entity) {
    }

    public void delete(Integer id) {
    }
}
