package by.academy.it.command;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DeleteFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("flight_id"));
        FlightDaoImpl flightDao;
        Connection connection;
        String page = null;
        try {
            connection = DataSource.getInstance().getConnection();
            flightDao = new FlightDaoImpl(connection);
            flightDao.delete(id);
            page = ConfigurationManager.getProperty("path.page.main");
            List<Flight> flights = flightDao.getAll();
            request.setAttribute("flights", flights);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
