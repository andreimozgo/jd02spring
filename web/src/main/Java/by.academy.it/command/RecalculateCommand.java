package by.academy.it.command;

import by.academy.it.entity.Flight;
import by.academy.it.entity.Service;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightServiceImpl;
import by.academy.it.services.ServiceServiceImpl;
import by.academy.it.services.TicketServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RecalculateCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        Ticket ticket = ticketService.findEntityById(ticketId);
        int cost = ticket.getCost();
        ServiceServiceImpl serviceServiceImpl = ServiceServiceImpl.getInstance();
        if (Integer.parseInt(request.getParameter("baggage")) == 1) {
            Service service = serviceServiceImpl.findEntityById(1);
            cost += service.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityregistration")) == 1) {
            Service service = serviceServiceImpl.findEntityById(2);
            cost += service.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityboarding")) == 1) {
            Service service = serviceServiceImpl.findEntityById(3);
            cost += service.getCost();
        }
        ticket.setCost(cost);
        ticketService.update(ticket);
        ticketService.findEntityById(ticketId);
        List<Flight> flights = FlightServiceImpl.getInstance().getAll();
        request.setAttribute("flights", flights);
        int userId = (Integer) session.getAttribute("userid");
        List<Ticket> tickets = ticketService.getAllByUser(userId);
        request.setAttribute("tickets", tickets);

        page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
