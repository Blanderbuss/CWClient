package com.cw.models;

public class Fighter {

    private String name;
    private String equipped;

    public Fighter() {
    }

    public Fighter(String name, String equipped) {
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
}
