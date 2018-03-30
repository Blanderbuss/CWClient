package com.cw.models.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BattleType {

    @NotNull
    @Min(0)
    private int id;
    @NotNull
    @Max(10)
    private long playersCount;
    @NotNull
    private String battleground;

    public BattleType(long playersCount, String battleground){
        this.playersCount = playersCount;
        this.battleground = battleground;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayersCount(long playersCount) {
        this.playersCount = playersCount;
    }

    public void setBattleground(String battleground) {
        this.battleground = battleground;
    }

    public int getId() {

        return id;
    }

    public long getPlayersCount() {
        return playersCount;
    }

    public String getBattleground() {
        return battleground;
    }
}
