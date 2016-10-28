package by.academy.it.services;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class BuyTicketCommand implements ActionCommand {


    public String execute(HttpServletRequest request) {
		final Logger LOG = Logger.getLogger(BuyTicketCommand.class);
        String page = null;
        HttpSession session = request.getSession(true);
        int flightId = Integer.parseInt(request.getParameter("flight_id"));
        int userId = (Integer) session.getAttribute("userid");

        try {
            Connection connectionDb = DataSource.getInstance().getConnection();
            FlightDaoImpl flightDao = new FlightDaoImpl(connectionDb);
            Flight flight = flightDao.findEntityById(flightId);
            int cost = flight.getCost();

            Ticket ticket = new Ticket(0, flightId, userId, cost, 0);
            TicketDaoImpl ticketDao = new TicketDaoImpl(connectionDb);
            ticketDao.create(ticket);
                page = ConfigurationManager.getProperty("path.page.user");
				LOG.info("User " + userId + " added ticket succesfully");

            List<Ticket> tickets = ticketDao.getAllByUser(userId);
            request.setAttribute("tickets", tickets);

            FlightDaoImpl fd = new FlightDaoImpl(connectionDb);
            List<Flight> flights = fd.getAll();
            request.setAttribute("flights", flights);

            try {
                connectionDb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return page;
    }

}
