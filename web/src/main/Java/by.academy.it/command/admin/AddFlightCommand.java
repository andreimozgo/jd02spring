package by.academy.it.command.admin;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.services.FlightServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddFlightCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page;
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String date = request.getParameter("flightDate");
            int seats = Integer.parseInt(request.getParameter("seats"));
            int cost = Integer.parseInt(request.getParameter("cost"));
            byte upCost = Byte.parseByte(request.getParameter("upCost"));
            Flight flight = new Flight(0, date, seats, cost, upCost);
            FlightServiceImpl.getInstance().create(flight);
            List<Flight> flights = FlightServiceImpl.getInstance().getAll();
            request.setAttribute("flights", flights);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}