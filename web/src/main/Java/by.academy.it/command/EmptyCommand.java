package by.academy.it.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(EmptyCommand.class);
        /*
         * if error occurred or direct call controller
		 *  forwarding to login page
		 */
        LOG.info("Empty command");
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
