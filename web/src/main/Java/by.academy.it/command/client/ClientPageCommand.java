package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightService;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class ClientPageCommand implements ActionCommand {

    @Autowired
    private FlightService flightService;
    @Autowired
    private TicketService ticketService;

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(ClientPageCommand.class);
        String page;
        int currentPage;
        int recordsPerPage;
        String flightDate;
        List<Flight> flights;

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
        int numberOfPages;
        if (request.getParameter("flightDate") != null) {
            flightDate = request.getParameter("flightDate");
            LOG.info("FlightDate= " + flightDate);
            flights = flightService.getAll(recordsPerPage, currentPage, flightDate);
            numberOfPages = flightService.getNumberOfPages(recordsPerPage, flightDate);
        } else {
            flights = flightService.getAll(recordsPerPage, currentPage);
            numberOfPages = flightService.getNumberOfPages(recordsPerPage);
        }

        request.setAttribute("flights", flights);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
