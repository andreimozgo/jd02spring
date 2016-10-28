package by.academy.it.services;

import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.User;

import java.sql.Connection;

public class LoginLogic {

    public static boolean checkLogin(String enterLogin, String enterPass, Connection connectionDb) {

        boolean passCheckResult = false;

        if (enterLogin.equals("") || enterPass.equals("")) {
            return passCheckResult;
        }

        UserDaoImpl userDao = new UserDaoImpl(connectionDb);
        passCheckResult = userDao.getPassword(enterLogin).equals(enterPass);
        return passCheckResult;
    }

    public static String getUserRole(String enterLogin, Connection connectionDb) {

        UserDaoImpl userDao = new UserDaoImpl(connectionDb);
        User user = userDao.getUserByLogin(enterLogin);
        return user.getUserRole();
    }
}
