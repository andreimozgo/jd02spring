package by.academy.it.command;

import by.academy.it.entity.Flight;
import by.academy.it.services.FlightServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page;
        String date = request.getParameter("flightDate");
        int seats = Integer.parseInt(request.getParameter("seats"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        byte upCost = Byte.parseByte(request.getParameter("upCost"));
        Flight flight = new Flight(0, date, seats, cost, upCost);
        FlightServiceImpl.getInstance().create(flight);
        page = ConfigurationManager.getProperty("path.page.main");
        List<Flight> flights = FlightServiceImpl.getInstance().getAll();
        request.setAttribute("flights", flights);
        return page;
    }
}