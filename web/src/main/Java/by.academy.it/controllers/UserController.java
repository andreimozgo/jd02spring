package by.academy.it.controllers;

import by.academy.it.entity.User;
import by.academy.it.manager.ConfigurationManager;
import by.academy.it.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminController adminController;

    @Autowired
    private ClientController clientController;

    final Logger LOG = Logger.getLogger(UserController.class);

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

  /*  @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String userCheck(HttpSession session, HttpServletRequest request, Locale locale,
                            @RequestParam(value = "login") String login,
                            @RequestParam(value = "password") String password) {
        String page;
        String userRole;
        // login and password check
        if (userService.checkLogin(login, password)) {
            session.setAttribute("user", login);
            // getting user role
            LOG.info("Truing to get user" + login);
            User user = userService.getUserByLogin(login);
            LOG.info("User " + login + " got successfully");
            userRole = user.getUserRole();
            int id = user.getId();
            // setting user role to session
            session.setAttribute("role", userRole);
            session.setAttribute("userid", id);
            session.setAttribute("user", login);
            LOG.info("User " + login + " logged in successfully");

            // getting main.jsp page depending on user role
            if (userRole.equals("admin")) {

                page = adminController.getAdminPage(request);
            } else {
                page = clientController.getClientPage(request);
            }
        } else {
            request.setAttribute("errorLoginPassMessage", messageSource.getMessage("message.loginerror", null, locale));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }*/

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        LOG.info("User logged out successfully");
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }

    @RequestMapping(value = "/addRegistration", method = RequestMethod.POST)
    public String addRegistration(@RequestParam(value = "login") String login,
                                  @RequestParam(value = "password") String password) {

        String page;
        User user = new User(login, password);
        userService.createOrUpdate(user);
        LOG.info("New registration added successfully");

        page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }

    @RequestMapping(value = "/access_denied", method = RequestMethod.GET)
    public String accessDeniedPage() {
        String page = ConfigurationManager.getProperty("path.page.assessdenied");
        return page;
    }
}
