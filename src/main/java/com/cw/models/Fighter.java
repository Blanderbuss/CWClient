package com.cw.models;

import java.util.ArrayList;

public class Fighter extends FighterA{

    public Fighter() {
    }

    public Fighter(String name, int lvl) {
        super(name, lvl);
    }

    @Override
    public ActTarget doAction(ArrayList<FighterA> fighters) {
        return new ActTarget(Action.DEFEND, 0);
    }
}
