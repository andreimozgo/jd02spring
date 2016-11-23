package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.entity.Extra;
import by.academy.it.entity.Ticket;
import by.academy.it.services.impl.ExtraServiceImpl;
import by.academy.it.services.impl.TicketServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RecalculateCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(RecalculateCommand.class);
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        String page;

        //HttpSession session = request.getSession(true);
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        Ticket ticket = ticketService.findEntityById(ticketId);
        int cost = ticket.getCost();
        ExtraServiceImpl extraServiceImpl = ExtraServiceImpl.getInstance();
        if (Integer.parseInt(request.getParameter("baggage")) == 1) {
            Extra extra = extraServiceImpl.findEntityById(1);
            ticket.getExtras().add(extra);
            cost += extra.getCost();
        }
        if (Integer.parseInt(request.getParameter("baggage")) == 0) {
            Extra extra = extraServiceImpl.findEntityById(1);
            ticket.getExtras().remove(extra);
            cost -= extra.getCost();
        }

        if (Integer.parseInt(request.getParameter("priorityregistration")) == 1) {
            Extra extra = extraServiceImpl.findEntityById(2);
            ticket.getExtras().add(extra);
            cost += extra.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityregistration")) == 0) {
            Extra extra = extraServiceImpl.findEntityById(2);
            ticket.getExtras().remove(extra);
            cost -= extra.getCost();
        }

        if (Integer.parseInt(request.getParameter("priorityboarding")) == 1) {
            Extra extra = extraServiceImpl.findEntityById(3);
            ticket.getExtras().add(extra);
            cost += extra.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityboarding")) == 0) {
            Extra extra = extraServiceImpl.findEntityById(3);
            ticket.getExtras().remove(extra);
            cost -= extra.getCost();
        }
        ticket.setCost(cost);
        ticketService.createOrUpdate(ticket);
        LOG.info("Ticket recalculated successfully");

        page = new ClientPageCommand().execute(request);

        //page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
