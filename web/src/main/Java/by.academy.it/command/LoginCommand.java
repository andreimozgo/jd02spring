package by.academy.it.command;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.entity.User;
import by.academy.it.services.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request) {

        String page;
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // проверка логина и пароля
        if (LoginLogic.checkLogin(login, pass)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", login);
            // получение роли пользователя
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            User user = userDao.getUserByLogin(login);
            String userRole = user.getUserRole();
            int id = user.getId();
            // помещение роли в сессию
            session.setAttribute("role", userRole);
            session.setAttribute("userid", id);
            session.setAttribute("user", login);

            TicketDaoImpl ticketDao = TicketDaoImpl.getInstance();
            List<Ticket> tickets = ticketDao.getAllByUser(id);
            request.setAttribute("tickets", tickets);
            // определение пути к main.jsp
            if (userRole.equals("admin")) {
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                page = ConfigurationManager.getProperty("path.page.user");
            }

            FlightDaoImpl fd = FlightDaoImpl.getInstance();
            List<Flight> flights = fd.getAll();
            request.setAttribute("flights", flights);
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
