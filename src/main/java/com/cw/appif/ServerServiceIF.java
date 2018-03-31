package com.cw.appif;

import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserException;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.util.List;


public interface ServerServiceIF {

    boolean register(User user) throws UserException;

    boolean authentificate(User user) throws UserException;

    boolean addSet(Set set, User user) throws FighterException;

    int readyForFight(int id, String battleFieldType) throws FighterException;

    String getResult(int indexOfBattleField, String typeOfBattleField);
}
