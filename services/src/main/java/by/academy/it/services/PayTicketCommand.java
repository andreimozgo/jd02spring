package by.academy.it.services;

import by.academy.it.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PayTicketCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {

        String page = null;
        HttpSession session = request.getSession(true);
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        try {
            Connection connectionDb = DataSource.getInstance().getConnection();
            TicketDAO ticketDao = new TicketDAO(connectionDb);
            Ticket ticket = ticketDao.findEntityById(ticketId);
            ticket.setPaid(1);
            ticketDao.update(ticket);

            FlightDAO fd = new FlightDAO(connectionDb);
            List<Flight> flights = fd.getAll();
            request.setAttribute("flights", flights);

            int userId = (Integer) session.getAttribute("userid");
            List<Ticket> tickets = ticketDao.getAllByUser(userId);
            request.setAttribute("tickets", tickets);

            page = ConfigurationManager.getProperty("path.page.user");
            try {
                connectionDb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (PropertyVetoException e2) {
            e2.printStackTrace();
        }
        return page;

    }

}
