package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightServiceImpl;
import by.academy.it.services.TicketServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminPageCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(AdminPageCommand.class);
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        FlightServiceImpl flightService = FlightServiceImpl.getInstance();
        String page;
        int currentPage;
        int recordsPerPage;

        HttpSession session = request.getSession(true);
        List<Ticket> tickets = ticketService.getAll();
        request.setAttribute("tickets", tickets);

        recordsPerPage = 3;
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

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
