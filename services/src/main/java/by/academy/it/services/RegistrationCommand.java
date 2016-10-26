package by.academy.it.services;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        System.out.println("Зашли в RegistrationCommand");
        String page = ConfigurationManager.getProperty("path.page.registration");
        return page;
    }

}
