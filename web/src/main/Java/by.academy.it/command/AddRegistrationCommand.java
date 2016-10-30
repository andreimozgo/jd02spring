package by.academy.it.command;

import by.academy.it.entity.User;
import by.academy.it.services.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class AddRegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {

        String page;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);
        UserServiceImpl.getInstance().create(user);
        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
