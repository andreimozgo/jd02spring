package by.academy.it.command;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.dao.impl.ServiceDaoImpl;
import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Service;
import by.academy.it.entity.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecalculateCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));

        Connection connectionDb = DataSource.getInstance().getConnection();
        TicketDaoImpl ticketDao = new TicketDaoImpl(connectionDb);
        Ticket ticket = ticketDao.findEntityById(ticketId);
        int cost = ticket.getCost();

        ServiceDaoImpl serviceDaoImpl = new ServiceDaoImpl(connectionDb);

        if (Integer.parseInt(request.getParameter("baggage")) == 1) {
            Service service = serviceDaoImpl.findEntityById(1);
            cost += service.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityregistration")) == 1) {
            Service service = serviceDaoImpl.findEntityById(2);
            cost += service.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityboarding")) == 1) {
            Service service = serviceDaoImpl.findEntityById(3);
            cost += service.getCost();
        }
        ticket.setCost(cost);
        ticketDao.update(ticket);
        ticketDao.findEntityById(ticketId);

        FlightDaoImpl fd = new FlightDaoImpl(connectionDb);
        List<Flight> flights = fd.getAll();
        request.setAttribute("flights", flights);

        int userId = (Integer) session.getAttribute("userid");
        List<Ticket> tickets = ticketDao.getAllByUser(userId);
        request.setAttribute("tickets", tickets);

        page = ConfigurationManager.getProperty("path.page.user");
        try {
            connectionDb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
