package by.academy.it.command;

import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.User;

import javax.servlet.http.HttpServletRequest;

public class AddRegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {

        String page = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        userDao.create(user);
        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
