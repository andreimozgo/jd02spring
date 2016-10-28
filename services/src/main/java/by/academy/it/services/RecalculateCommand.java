package by.academy.it.services;

import by.academy.it.dao.*;
import by.academy.it.dao.datasource.DataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecalculateCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));

        try {
            Connection connectionDb = DataSource.getInstance().getConnection();
            TicketDAO ticketDao = new TicketDAO(connectionDb);
            Ticket ticket = ticketDao.findEntityById(ticketId);
            int cost = ticket.getCost();

            ServicesDAO servicesDao = new ServicesDAO(connectionDb);

            if (Integer.parseInt(request.getParameter("baggage")) == 1) {

                Services service = servicesDao.findEntityById(1);
                cost += service.getCost();
                System.out.println(cost + "кост");
            }
            if (Integer.parseInt(request.getParameter("priorityregistration")) == 1) {
                Services service = servicesDao.findEntityById(2);
                cost += service.getCost();
            }
            if (Integer.parseInt(request.getParameter("priorityboarding")) == 1) {
                Services service = servicesDao.findEntityById(3);
                cost += service.getCost();
            }
            ticket.setCost(cost);
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
