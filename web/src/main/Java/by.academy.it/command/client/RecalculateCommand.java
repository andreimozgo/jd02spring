package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.entity.Extra;
import by.academy.it.entity.Ticket;
import by.academy.it.services.ExtraService;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RecalculateCommand implements ActionCommand {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private ExtraService extraService;

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(RecalculateCommand.class);
        String page;

        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        Ticket ticket = ticketService.findEntityById(ticketId);
        int cost = ticket.getCost();
        if (Integer.parseInt(request.getParameter("baggage")) == 1) {
            Extra extra = extraService.findEntityById(1);
            ticket.getExtras().add(extra);
            cost += extra.getCost();
        }
        if (Integer.parseInt(request.getParameter("baggage")) == 0) {
            Extra extra = extraService.findEntityById(1);
            ticket.getExtras().remove(extra);
            cost -= extra.getCost();
        }

        if (Integer.parseInt(request.getParameter("priorityregistration")) == 1) {
            Extra extra = extraService.findEntityById(2);
            ticket.getExtras().add(extra);
            cost += extra.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityregistration")) == 0) {
            Extra extra = extraService.findEntityById(2);
            ticket.getExtras().remove(extra);
            cost -= extra.getCost();
        }

        if (Integer.parseInt(request.getParameter("priorityboarding")) == 1) {
            Extra extra = extraService.findEntityById(3);
            ticket.getExtras().add(extra);
            cost += extra.getCost();
        }
        if (Integer.parseInt(request.getParameter("priorityboarding")) == 0) {
            Extra extra = extraService.findEntityById(3);
            ticket.getExtras().remove(extra);
            cost -= extra.getCost();
        }
        ticket.setCost(cost);
        ticketService.createOrUpdate(ticket);
        LOG.info("Ticket recalculated successfully");

        page = new ClientPageCommand().execute(request);
        return page;
    }
}
