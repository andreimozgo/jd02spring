package by.academy.it.services;

import by.academy.it.dao.impl.UserDaoImpl;
import by.academy.it.entity.User;

public class UserServiceImpl implements IService<User> {
    private static UserServiceImpl instance = null;

    public UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) instance = new UserServiceImpl();
        return instance;
    }

    public boolean checkLogin(String enterLogin, String enterPass) {
        boolean passCheckResult = false;
        if (enterLogin.equals("") || enterPass.equals("")) {
            return passCheckResult;
        }
        passCheckResult = UserDaoImpl.getInstance().getPassword(enterLogin).equals(enterPass);
        return passCheckResult;
    }

    public void create(User user) {
        UserDaoImpl.getInstance().create(user);
    }

    public User findEntityById(Integer id) {
        return null;
    }

    public void update(User user) {

    }

    public void delete(Integer id) {

    }

    public User getUserByLogin(String login) {
        return UserDaoImpl.getInstance().getUserByLogin(login);
    }

    public String getUserRole(String enterLogin) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user = userDao.getUserByLogin(enterLogin);
        return user.getUserRole();
    }
}
