package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.impl.FlightServiceImpl;
import by.academy.it.services.impl.TicketServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BuyTicketCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(BuyTicketCommand.class);
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        FlightServiceImpl flightService = FlightServiceImpl.getInstance();
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

        //page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
