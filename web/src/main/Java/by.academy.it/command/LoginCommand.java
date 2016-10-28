package by.academy.it.command;

import by.academy.it.dao.impl.FlightDaoImpl;
import by.academy.it.dao.impl.TicketDaoImpl;
import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.entity.User;
import by.academy.it.services.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request) {

        String page = null;
        Connection connectionDb = DataSource.getInstance().getConnection();
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // проверка логина и пароля
        if (LoginLogic.checkLogin(login, pass, connectionDb)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", login);
            // получение роли пользователя
            UserDaoImpl userDao = new UserDaoImpl(connectionDb);
            User user = userDao.getUserByLogin(login);
            String userRole = user.getUserRole();
            int id = user.getId();
            // помещение роли в сессию
            session.setAttribute("role", userRole);
            session.setAttribute("userid", id);
            session.setAttribute("user", login);

            TicketDaoImpl ticketDao = new TicketDaoImpl(connectionDb);
            List<Ticket> tickets = ticketDao.getAllByUser(id);
            request.setAttribute("tickets", tickets);
            // определение пути к main.jsp
            if (userRole.equals("admin")) {
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                page = ConfigurationManager.getProperty("path.page.user");
            }

            FlightDaoImpl fd = new FlightDaoImpl(connectionDb);
            List<Flight> flights = fd.getAll();
            request.setAttribute("flights", flights);
            try {
                connectionDb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

        return page;
    }
}
