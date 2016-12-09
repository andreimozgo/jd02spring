package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        return page;
    }
}
