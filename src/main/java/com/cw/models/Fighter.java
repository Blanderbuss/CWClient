package com.cw.models;

public class Fighter extends FighterA{

    @Override
    public Action doAction() {
        return Action.DEFEND;
    }
}
