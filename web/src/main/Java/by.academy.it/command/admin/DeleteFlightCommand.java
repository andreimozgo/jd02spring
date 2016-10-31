package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.services.FlightServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DeleteFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(DeleteFlightCommand.class);
        String page;
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            int id = Integer.parseInt(request.getParameter("flight_id"));
            FlightServiceImpl.getInstance().delete(id);
            List<Flight> flights = FlightServiceImpl.getInstance().getAll();
            request.setAttribute("flights", flights);
            connection.commit();
            LOG.info("Flight deleted successfully");
            connection.close();
        } catch (SQLException e) {
            LOG.error("Exception", e);
        }

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
