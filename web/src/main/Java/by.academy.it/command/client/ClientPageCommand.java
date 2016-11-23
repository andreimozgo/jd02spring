package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.impl.FlightServiceImpl;
import by.academy.it.services.impl.TicketServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientPageCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(ClientPageCommand.class);
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        FlightServiceImpl flightService = FlightServiceImpl.getInstance();
        String page;
        int currentPage;
        int recordsPerPage;

        HttpSession session = request.getSession(true);
        int userId = (Integer) session.getAttribute("userid");
        List<Ticket> tickets = ticketService.getAllByUser(userId);
        request.setAttribute("tickets", tickets);

        if (request.getParameter("recordsPerPage") != null) {
            recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        } else {
            recordsPerPage = 3;
        }

        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.valueOf(request.getParameter("currentPage"));
        } else {
            currentPage = 1;
        }
        int numberOfPages = flightService.getNumberOfPages(recordsPerPage);
        List<Flight> flights = flightService.getAll(recordsPerPage, currentPage);
        request.setAttribute("flights", flights);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
