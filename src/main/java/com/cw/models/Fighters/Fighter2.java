package com.cw.models.Fighters;

import com.cw.models.FighterA;

import java.util.ArrayList;

public class Fighter2 extends FighterA {


    /**
     * @param name is given through user input
     * @param lvl  is given through getting it from user
     */
    public Fighter2(String name, int lvl) {
        super(name, lvl);
    }

    @Override
    public ActTarget doAction(ArrayList<FighterA> fighters) {
        return new ActTarget(Action.ATTACK, 0);
    }
}
