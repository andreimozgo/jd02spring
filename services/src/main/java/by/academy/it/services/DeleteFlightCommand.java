package by.academy.it.services;

import by.academy.it.dao.DataSource;
import by.academy.it.dao.Flight;
import by.academy.it.dao.FlightDAO;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DeleteFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("flight_id"));
        FlightDAO flightDao;
        Connection connection;
        String page = null;
        try {
            connection = DataSource.getInstance().getConnection();
            flightDao = new FlightDAO(connection);
            flightDao.delete(id);
            page = ConfigurationManager.getProperty("path.page.main");
            List<Flight> flights = flightDao.getAll();
            request.setAttribute("flights", flights);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return page;
    }
}
