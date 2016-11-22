package by.academy.it.controller;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ActionFactory;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.command.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    private static final long serialVersionUID = -6668349208729370249L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        // get the command from JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        // call execute() and forward request
        page = command.execute(request); // gives answer page
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            // calls response page
            dispatcher.forward(request, response);
        } else {
            // gets error page
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}