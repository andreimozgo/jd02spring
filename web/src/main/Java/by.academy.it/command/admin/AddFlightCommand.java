package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.entity.Flight;
import by.academy.it.services.impl.FlightServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(AddFlightCommand.class);
        FlightServiceImpl flightService = FlightServiceImpl.getInstance();
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