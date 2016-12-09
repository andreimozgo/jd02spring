package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.entity.User;
import by.academy.it.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AddRegistrationCommand implements ActionCommand {

    @Autowired
    private UserService userService;

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(AddRegistrationCommand.class);
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
