package by.academy.it.dao;

import by.academy.it.entity.User;

public interface UserDao extends Dao<User> {

    String getPassword(String login);

    User getUserByLogin(String login);

}
