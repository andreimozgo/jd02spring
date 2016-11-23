package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.command.MessageManager;
import by.academy.it.command.admin.AdminPageCommand;
import by.academy.it.command.client.ClientPageCommand;
import by.academy.it.entity.User;
import by.academy.it.services.FlightServiceImpl;
import by.academy.it.services.TicketServiceImpl;
import by.academy.it.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(LoginCommand.class);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        TicketServiceImpl ticketService = TicketServiceImpl.getInstance();
        FlightServiceImpl flightService = FlightServiceImpl.getInstance();
        String page;
        String userRole;
        int currentPage;
        int recordsPerPage;

        // getting login and password from request
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        // login and password check
        if (userService.checkLogin(login, pass)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", login);
            // getting user role
            User user = userService.getUserByLogin(login);
            userRole = user.getUserRole();
            int id = user.getId();
            // setting user role to session
            session.setAttribute("role", userRole);
            session.setAttribute("userid", id);
            session.setAttribute("user", login);
            LOG.info("User " + login + " logged in successfully");

            // getting main.jsp page depending on user role
            if (userRole.equals("admin")) {
                page = new AdminPageCommand().execute(request);
                //page = ConfigurationManager.getProperty("path.page.main");
            } else {
                page = new ClientPageCommand().execute(request);
                //page = ConfigurationManager.getProperty("path.page.user");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
