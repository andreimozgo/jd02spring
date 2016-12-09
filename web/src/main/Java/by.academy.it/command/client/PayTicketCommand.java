package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.services.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PayTicketCommand implements ActionCommand {

    @Autowired
    private TicketService ticketService;

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(PayTicketCommand.class);
        String page;

        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        ticketService.payTicket(ticketId);
        LOG.info("Ticket payed successfully");

        page = new ClientPageCommand().execute(request);
        return page;
    }
}
