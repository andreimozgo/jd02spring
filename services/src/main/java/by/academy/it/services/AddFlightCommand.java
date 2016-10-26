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

public class AddFlightCommand implements ActionCommand {


    public String execute(HttpServletRequest request) {

        FlightDAO flightDao;
        Connection connection;
        String page = null;
        try {
            connection = DataSource.getInstance().getConnection();
            flightDao = new FlightDAO(connection);
            String date = request.getParameter("flightDate");
            int seats = Integer.parseInt(request.getParameter("seats"));
            int cost = Integer.parseInt(request.getParameter("cost"));
            byte upCost = Byte.parseByte(request.getParameter("upCost"));

            Flight flight = new Flight(0, date, seats, cost, upCost);

            flightDao.create(flight);
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