package by.academy.it.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}