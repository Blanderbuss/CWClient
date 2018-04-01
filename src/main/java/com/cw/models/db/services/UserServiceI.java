package com.cw.models.db.services;

import com.cw.models.entities.User;

/**
 * Created by denysmelnychenko on 2/11/18.
 */
public interface UserServiceI {
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password);

    boolean addUser(User user);
    boolean updateUser(User user);

    boolean deleteUserById(int id);
    User deleteUser(User user);

    void closeConnection();

    //TODO this method. Add "throws AuthentificationException"
    void authentificate(User user);
    //TODO this method. Add "throws AuthentificationException"
    void register(User user);
}