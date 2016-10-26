package by.academy.it.services;

import by.academy.it.dao.User;
import by.academy.it.dao.UsersDAO;

import java.sql.Connection;

public class LoginLogic {

    public static boolean checkLogin(String enterLogin, String enterPass, Connection connectionDb) {

        boolean passCheckResult = false;

        if (enterLogin.equals("") || enterPass.equals("")) {
            return passCheckResult;
        }

        UsersDAO userDao = new UsersDAO(connectionDb);
        passCheckResult = userDao.getPassword(enterLogin).equals(enterPass);
        return passCheckResult;
    }

    public static String getUserRole(String enterLogin, Connection connectionDb) {

        String userRole = "user";

        UsersDAO userDao = new UsersDAO(connectionDb);
        User user = userDao.getUserByLogin(enterLogin);
        userRole = user.getUserRole();
        return userRole;
    }
}
