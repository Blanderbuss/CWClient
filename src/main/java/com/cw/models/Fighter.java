package com.cw.models;

import java.util.ArrayList;

public class Fighter extends FighterA{

    @Override
    public ActTarget doAction(ArrayList<FighterA> fighters) {
        return new ActTarget(Action.DEFEND, 0);
    }
}
