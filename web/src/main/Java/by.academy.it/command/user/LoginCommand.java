package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.command.MessageManager;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.entity.User;
import by.academy.it.services.FlightServiceImpl;
import by.academy.it.services.TicketServiceImpl;
import by.academy.it.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request) {
        String page;
        String userRole = "user";
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        // проверка логина и пароля
        if (UserServiceImpl.getInstance().checkLogin(login, pass)) {
            Connection connection = DataSource.getInstance().getConnection();
            try {
                connection.setAutoCommit(false);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", login);
                // получение роли пользователя
                User user = UserServiceImpl.getInstance().getUserByLogin(login);
                userRole = user.getUserRole();
                int id = user.getId();
                // помещение роли в сессию
                session.setAttribute("role", userRole);
                session.setAttribute("userid", id);
                session.setAttribute("user", login);
                List<Ticket> tickets = TicketServiceImpl.getInstance().getAllByUser(id);
                request.setAttribute("tickets", tickets);
                List<Flight> flights = FlightServiceImpl.getInstance().getAll();
                request.setAttribute("flights", flights);
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // определение пути к main.jsp
            if (userRole.equals("admin")) {
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                page = ConfigurationManager.getProperty("path.page.user");
            }
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
