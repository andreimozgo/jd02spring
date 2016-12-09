package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.services.FlightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteFlightCommand implements ActionCommand {
    @Autowired
    private FlightService flightService;

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(DeleteFlightCommand.class);
        String page;

        int id = Integer.parseInt(request.getParameter("flight_id"));
        flightService.delete(id);
        LOG.info("Flight deleted successfully");

        page = new AdminPageCommand().execute(request);
        return page;
    }
}
