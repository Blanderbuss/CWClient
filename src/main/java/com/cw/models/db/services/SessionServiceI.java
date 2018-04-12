package com.cw.models.db.services;

import com.cw.exceptions.UserNotFoundException;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.util.List;

public interface SessionServiceI {

    // starts a valid session; initializes user variable
    // returns user data if login was successful
    User login(String email, String pwd) throws UserNotFoundException;

    // finishes session
    void logout(User user);

    // indicates whether current session is active and valid
    boolean isLoggedIn(User user);
    boolean isLoggedIn(String email);

    // returns a user from which we can get his sets, artefacts and other fields
    User getUser(User user);

    // create a user
    // returns true if registration was successful, false otherwise
    boolean register(String usernme, String email, String pwd);

    void addNewSet(Set set, User user);

    // artefact should be present in user backpack (user artifact list)
    boolean addArtefactFromBackpackToCurrentSet(Artefact artefact, User user);

    void chooseSetAsCurrent(Set set, User user);

    //TODO add methods to modify and delete user sets

    // starts a fight
    void startFightAgainstBot(User user);

    void startFightAgainstUser(User user);

    String getMyUserStatus(User user);

    // all users that are online whose status is ready-to-fight
    List<User> getUsersReadyToFight();

    String getFightStatistics(User user);
}
