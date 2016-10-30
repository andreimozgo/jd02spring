package by.academy.it.command;

import by.academy.it.entity.Flight;
import by.academy.it.services.FlightServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("flight_id"));
        String page;

        FlightServiceImpl.getInstance().delete(id);
        List<Flight> flights = FlightServiceImpl.getInstance().getAll();
        request.setAttribute("flights", flights);

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
