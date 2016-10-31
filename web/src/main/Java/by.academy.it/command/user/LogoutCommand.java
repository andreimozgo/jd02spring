package by.academy.it.command.user;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(LogoutCommand.class);
        request.getSession().invalidate();
        LOG.info("User logged out successfully");
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}