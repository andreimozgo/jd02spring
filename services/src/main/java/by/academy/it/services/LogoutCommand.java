package by.academy.it.services;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        // уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}
