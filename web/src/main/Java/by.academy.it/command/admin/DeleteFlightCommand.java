package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.entity.Flight;
import by.academy.it.services.FlightServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(DeleteFlightCommand.class);
        String page;

        int id = Integer.parseInt(request.getParameter("flight_id"));
        FlightServiceImpl.getInstance().delete(id);
        List<Flight> flights = FlightServiceImpl.getInstance().getAll();
        request.setAttribute("flights", flights);
        LOG.info("Flight deleted successfully");

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
