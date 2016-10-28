package by.academy.it.services;

import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.datasource.DataSource;
import by.academy.it.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddRegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {

        String page = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);

        try {
            Connection connectionDb = DataSource.getInstance().getConnection();
            UserDaoImpl userDao = new UserDaoImpl(connectionDb);
            userDao.create(user);
                page = ConfigurationManager.getProperty("path.page.login");
            try {
                connectionDb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return page;
    }

}
