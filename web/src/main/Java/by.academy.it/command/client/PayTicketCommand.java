package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightServiceImpl;
import by.academy.it.services.TicketServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PayTicketCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page;
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            HttpSession session = request.getSession(true);
            int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
            Ticket ticket = TicketServiceImpl.getInstance().findEntityById(ticketId);
            ticket.setPaid(1);
            TicketServiceImpl.getInstance().update(ticket);

            List<Flight> flights = FlightServiceImpl.getInstance().getAll();
            request.setAttribute("flights", flights);
            int userId = (Integer) session.getAttribute("userid");
            List<Ticket> tickets = TicketServiceImpl.getInstance().getAllByUser(userId);
            request.setAttribute("tickets", tickets);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
