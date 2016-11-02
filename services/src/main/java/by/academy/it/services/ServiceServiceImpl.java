package by.academy.it.services;

import by.academy.it.dao.impl.ServiceDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceServiceImpl implements IService<Service> {
    private static ServiceServiceImpl instance = null;
    final Logger LOG = Logger.getLogger(ServiceServiceImpl.class);

    public ServiceServiceImpl() {
    }

    public static synchronized ServiceServiceImpl getInstance() {
        if (instance == null) instance = new ServiceServiceImpl();
        return instance;
    }

    public void create(Service service) {

    }

    public Service findEntityById(Integer id) {
        Connection connection = DataSource.getInstance().getConnection();
        Service service = null;
        try {
            connection.setAutoCommit(false);
            service = ServiceDaoImpl.getInstance().findEntityById(id);
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
        return service;
    }

    public void update(Service service) {

    }

    public void delete(Integer id) {

    }
}
