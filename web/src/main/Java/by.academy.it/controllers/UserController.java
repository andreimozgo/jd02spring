package by.academy.it.controllers;

import by.academy.it.manager.ConfigurationManager;
import by.academy.it.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        String page = ConfigurationManager.getProperty("path.page.registration");
        return page;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}
