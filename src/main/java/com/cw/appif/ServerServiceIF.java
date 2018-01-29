package com.cw.appif;

import com.cw.exceptions.FighterException;
import com.cw.exceptions.UserException;
import com.cw.models.FighterA;
import com.cw.models.User;


public interface ServerServiceIF{

    boolean register(User user) throws UserException;

    boolean auth(User user) throws UserException;

    boolean regFighter(FighterA fighter) throws FighterException;

}
