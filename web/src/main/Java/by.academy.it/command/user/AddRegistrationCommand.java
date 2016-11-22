package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.entity.User;
import by.academy.it.services.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddRegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(AddRegistrationCommand.class);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String page;

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);
        userService.createOrUpdate(user);
        LOG.info("New registration added successfully");

        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
