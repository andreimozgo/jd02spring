package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.User;
import by.academy.it.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public class AddRegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page;
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User user = new User(login, password);
            UserServiceImpl.getInstance().create(user);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
