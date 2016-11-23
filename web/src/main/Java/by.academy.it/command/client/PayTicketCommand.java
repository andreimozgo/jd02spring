package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.services.TicketServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PayTicketCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(PayTicketCommand.class);
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        String page;

        //HttpSession session = request.getSession(true);
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        ticketService.payTicket(ticketId);
        LOG.info("Ticket payed successfully");

        page = new ClientPageCommand().execute(request);

        //page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
