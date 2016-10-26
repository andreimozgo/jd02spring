package by.academy.it.services;

import by.academy.it.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BuyTicketCommand implements ActionCommand {


    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        int flightId = Integer.parseInt(request.getParameter("flight_id"));
        int userId = (Integer) session.getAttribute("userid");

        try {
            Connection connectionDb = DataSource.getInstance().getConnection();
            FlightDAO flightDao = new FlightDAO(connectionDb);
            Flight flight = flightDao.findEntityById(flightId);
            int cost = flight.getCost();

            Ticket ticket = new Ticket(0, flightId, userId, cost, 0);
            TicketDAO ticketDao = new TicketDAO(connectionDb);
            if (ticketDao.create(ticket)) {
                page = ConfigurationManager.getProperty("path.page.user");
            }

            List<Ticket> tickets = ticketDao.getAllByUser(userId);
            request.setAttribute("tickets", tickets);

            FlightDAO fd = new FlightDAO(connectionDb);
            List<Flight> flights = fd.getAll();
            request.setAttribute("flights", flights);

            try {
                connectionDb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return page;
    }

}
