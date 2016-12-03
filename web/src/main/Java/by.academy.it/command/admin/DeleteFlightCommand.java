package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.services.impl.FlightServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(DeleteFlightCommand.class);
        FlightServiceImpl flightService = FlightServiceImpl.getInstance();
        String page;

        int id = Integer.parseInt(request.getParameter("flight_id"));
        flightService.delete(id);
        LOG.info("Flight deleted successfully");

        page = new AdminPageCommand().execute(request);
        return page;
    }
}
