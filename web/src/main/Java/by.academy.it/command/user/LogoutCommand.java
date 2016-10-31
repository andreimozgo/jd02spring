package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}