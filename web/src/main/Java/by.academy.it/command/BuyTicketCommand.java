package by.academy.it.command;

import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightServiceImpl;
import by.academy.it.services.TicketServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BuyTicketCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(BuyTicketCommand.class);
        String page;
        HttpSession session = request.getSession(true);
        int flightId = Integer.parseInt(request.getParameter("flight_id"));
        int userId = (Integer) session.getAttribute("userid");

        Flight flight = FlightServiceImpl.getInstance().findEntityById(flightId);
        int cost = flight.getCost();
        Ticket ticket = new Ticket(0, flightId, userId, cost, 0);
        TicketServiceImpl.getInstance().create(ticket);
        List<Ticket> tickets = TicketServiceImpl.getInstance().getAllByUser(userId);
        request.setAttribute("tickets", tickets);
        List<Flight> flights = FlightServiceImpl.getInstance().getAll();
        request.setAttribute("flights", flights);

        page = ConfigurationManager.getProperty("path.page.user");
        LOG.info("User " + userId + " added ticket succesfully");
        return page;
    }
}
