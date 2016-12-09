package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.entity.Flight;
import by.academy.it.services.FlightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AddFlightCommand implements ActionCommand {

    @Autowired
    private FlightService flightService;

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(AddFlightCommand.class);
        String page;

        String date = request.getParameter("flightDate");
        int seats = Integer.parseInt(request.getParameter("seats"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        byte upCost = Byte.parseByte(request.getParameter("upCost"));
        Flight flight = new Flight(null, date, seats, cost, upCost);
        flightService.createOrUpdate(flight);
        LOG.info("Flight added successfully");

        page = new AdminPageCommand().execute(request);

        return page;
    }
}