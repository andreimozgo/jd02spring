package by.academy.it.services;

import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.entity.Ticket;

import java.util.List;

public class TicketServiceImpl implements IService<Ticket> {
    private static TicketServiceImpl instance = null;

    public TicketServiceImpl() {
    }

    public static synchronized TicketServiceImpl getInstance() {
        if (instance == null) instance = new TicketServiceImpl();
        return instance;
    }

    public List<Ticket> getAllByUser(int userId) {
        return TicketDaoImpl.getInstance().getAllByUser(userId);
    }

    public void create(Ticket ticket) {
        TicketDaoImpl.getInstance().create(ticket);
    }

    public Ticket findEntityById(Integer id) {
        return TicketDaoImpl.getInstance().findEntityById(id);
    }

    public void update(Ticket ticket) {
        TicketDaoImpl.getInstance().update(ticket);
    }

    public void delete(Integer id) {

    }
}
