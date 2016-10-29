package by.academy.it.command;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.entity.Flight;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page;
        FlightDaoImpl flightDao = FlightDaoImpl.getInstance();
        String date = request.getParameter("flightDate");
        int seats = Integer.parseInt(request.getParameter("seats"));
        int cost = Integer.parseInt(request.getParameter("cost"));
        byte upCost = Byte.parseByte(request.getParameter("upCost"));
        Flight flight = new Flight(0, date, seats, cost, upCost);
        flightDao.create(flight);
        page = ConfigurationManager.getProperty("path.page.main");
        List<Flight> flights = flightDao.getAll();
        request.setAttribute("flights", flights);
        return page;
    }
}