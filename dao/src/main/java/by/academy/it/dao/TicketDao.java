package by.academy.it.dao;

import by.academy.it.entity.Ticket;

import java.util.List;

public interface TicketDao extends Dao<Ticket> {

    List<Ticket> getAllByUser(int userId);
}
