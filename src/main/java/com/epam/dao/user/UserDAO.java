package com.epam.dao.user;


import com.epam.model.User;

import javax.sql.RowSet;
import java.util.List;

public interface UserDAO {

    public long insertUser(User user);
    public boolean deleteUser(User user);
    public User findUserByName(String name);
    public User findUserByLogin(String login);
    public User findUserById(long id);
    public User findFirstUserByName(String name);
    public boolean updateUser(User user);
    public RowSet selectUserRS(User user);
    public List<User> selectUserTO();
}
