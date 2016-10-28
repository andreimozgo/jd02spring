package by.academy.it.command;

import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public class AddRegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {

        String page = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);

        Connection connectionDb = DataSource.getInstance().getConnection();
        UserDaoImpl userDao = new UserDaoImpl(connectionDb);
        userDao.create(user);
        page = ConfigurationManager.getProperty("path.page.login");
        try {
            connectionDb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
