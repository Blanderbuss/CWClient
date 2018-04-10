package com.cw.models.db.services;

import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.util.List;

public interface SessionServiceI {

    // starts a valid session; initializes user variable
    // returns true if login was successful, false otherwise
    boolean login(String email, String pwd);

    // finishes session
    void logout();

    // indicates whether current session is active and valid
    boolean isLoggedIn();

    // returns a user from which we can get his sets, artefacts and other fields
    User getUser();

    // create a user
    // returns true if registration was successful, false otherwise
    boolean register(String username, String email, String pwd);

    void addNewSet(Set set);

    // artefact should be present in user backpack (user artifact list)
    boolean addArtefactFromBackpackToCurrentSet(Artefact artefact);

    void chooseSetAsCurrent(Set set);

    //TODO add methods to modify and delete user sets

    // starts a fight
    void startFightAgainstBot();

    void startFightAgainstUser(User u);

    String getMyUserStatus();

    // all users that are online whose status is ready-to-fight
    List<User> getUsersReadyToFight();

    String getFightStatistics();
}
