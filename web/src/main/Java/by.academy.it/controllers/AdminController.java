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
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private TicketService ticketService;

    final Logger LOG = Logger.getLogger(AdminController.class);

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(HttpServletRequest request) {
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
