package com.cw.models;

import java.util.ArrayList;

public class Fighter extends FighterA{

    @Override
    public actTarget doAction(ArrayList<FighterA> fighters) {
        return new actTarget(Action.ATTACK, fighters.get(0).getName());
    }
}
