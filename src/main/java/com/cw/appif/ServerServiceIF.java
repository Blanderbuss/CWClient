package com.cw.appif;

import com.cw.exceptions.AuthException;
import com.cw.models.User;


public interface ServerServiceIF{

    boolean auth(User user) throws AuthException;

}
