package by.academy.it.command;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.entity.Flight;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("flight_id"));
        FlightDaoImpl flightDao;
        String page;

        flightDao = FlightDaoImpl.getInstance();
        flightDao.delete(id);
        page = ConfigurationManager.getProperty("path.page.main");
        List<Flight> flights = flightDao.getAll();

        request.setAttribute("flights", flights);
        return page;
    }
}
