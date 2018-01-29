package com.cw.models;

import java.util.ArrayList;

public abstract class FighterA {
    //TODO More properties
    public class actTarget{
        private Action action;
        private String target;

        public actTarget(Action action, String target) {
            this.action = action;
            this.target = target;
        }
    }

    public enum Action {
        ATTACK,
        DEFEND
    }

    public enum Status {
        UNREGISTERED,
        REGISTERED,
        READY,
        FIGHTING
    }

    private String name;

    private int lvl;
    private String equipped;
    private Status status = Status.UNREGISTERED;

    private int curHp;
    private int curSpeed;

    public FighterA() { }

    public FighterA(String name, String equipped) {
        this.name = name;
        this.equipped = equipped;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public final String getEquipped() {
        return equipped;
    }

    public final void setEquipped(String equipped) {
        this.equipped = equipped;
    }

    public final Status getStatus() {
        return status;
    }

    public final void setStatus(Status status) {
        this.status = status;
    }

    public final int getCurHp() {
        return curHp;
    }

    public final void setCurHp(int curHp) {
        this.curHp = curHp;
    }

    public final int getCurSpeed() {
        return curSpeed;
    }

    public final void setCurSpeed(int curSpeed) {
        this.curSpeed = curSpeed;
    }

    public final boolean rest(){
        setCurSpeed(getCurSpeed() - 1);
        return getCurSpeed() == 0;
    }

    public abstract actTarget doAction(ArrayList<FighterA> fighters);
}
