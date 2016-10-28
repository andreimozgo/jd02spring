package by.academy.it.services;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;

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
            TicketDaoImpl ticketDao = new TicketDaoImpl(connectionDb);
            Ticket ticket = ticketDao.findEntityById(ticketId);
            ticket.setPaid(1);
            ticketDao.update(ticket);
            ticketDao.findEntityById(ticketId);

            FlightDaoImpl fd = new FlightDaoImpl(connectionDb);
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
