package by.academy.it.services;

import by.academy.it.dao.impl.ServiceDaoImpl;
import by.academy.it.entity.Service;

public class ServiceServiceImpl implements IService<Service> {
    private static ServiceServiceImpl instance = null;

    public ServiceServiceImpl() {
    }

    public static synchronized ServiceServiceImpl getInstance() {
        if (instance == null) instance = new ServiceServiceImpl();
        return instance;
    }

    public void create(Service service) {

    }

    public Service findEntityById(Integer id) {
        return ServiceDaoImpl.getInstance().findEntityById(id);
    }

    public void update(Service service) {

    }

    public void delete(Integer id) {

    }
}
