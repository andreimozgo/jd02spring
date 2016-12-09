package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightService;
import by.academy.it.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class AdminPageCommand implements ActionCommand {

    @Autowired
    private FlightService flightService;
    @Autowired
    private TicketService ticketService;

    public String execute(HttpServletRequest request) {
        String page;
        int currentPage;
        int recordsPerPage;

        List<Ticket> tickets = ticketService.getAll();
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
        List<Flight> flights = flightService.getAll(recordsPerPage, currentPage);
        int numberOfPages = flightService.getNumberOfPages(recordsPerPage);
        request.setAttribute("flights", flights);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
