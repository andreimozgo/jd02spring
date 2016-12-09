package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightService;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class BuyTicketCommand implements ActionCommand {

    @Autowired
    private FlightService flightService;
    @Autowired
    private TicketService ticketService;

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(BuyTicketCommand.class);
        String page;

        HttpSession session = request.getSession(true);
        int flightId = Integer.parseInt(request.getParameter("flight_id"));
        int userId = (Integer) session.getAttribute("userid");
        Flight flight = flightService.findEntityById(flightId);
        int cost = flight.getCost();
        Ticket ticket = new Ticket(0, flight, userId, cost, 0);
        ticketService.createOrUpdate(ticket);
        LOG.info("User bought ticket successfully");

        page = new ClientPageCommand().execute(request);
        return page;
    }
}
