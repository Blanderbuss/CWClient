package com.cw.models;

public abstract class FighterA {

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
    private String equipped;
    private Status status = Status.UNREGISTERED;


    public FighterA() {
    }

    public FighterA(String name, String equipped) {
        this.name = name;
        this.equipped = equipped;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipped() {
        return equipped;
    }

    public void setEquipped(String equipped) {
        this.equipped = equipped;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public abstract Action doAction();
}
