package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.impl.FlightServiceImpl;
import by.academy.it.services.impl.TicketServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminPageCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        FlightServiceImpl flightService = FlightServiceImpl.getInstance();
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
