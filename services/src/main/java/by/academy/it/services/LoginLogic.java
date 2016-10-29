package by.academy.it.services;

import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.User;

public class LoginLogic {

    public static boolean checkLogin(String enterLogin, String enterPass) {

        boolean passCheckResult = false;

        if (enterLogin.equals("") || enterPass.equals("")) {
            return passCheckResult;
        }

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        passCheckResult = userDao.getPassword(enterLogin).equals(enterPass);
        return passCheckResult;
    }

    public static String getUserRole(String enterLogin) {

        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user = userDao.getUserByLogin(enterLogin);
        return user.getUserRole();
    }
}
