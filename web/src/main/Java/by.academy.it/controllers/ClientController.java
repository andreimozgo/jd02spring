package by.academy.it.controllers;

import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.services.FlightService;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private TicketService ticketService;

    final Logger LOG = Logger.getLogger(ClientController.class);

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String getClientPage(HttpServletRequest request) {
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
