package com.cw.models.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class BattleType implements Serializable {

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

    public BattleType(BattleType other) {
        this.id = other.id;
        this.playersCount = other.playersCount;
        this.battleground = other.battleground;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleType that = (BattleType) o;
        return id == that.id &&
                playersCount == that.playersCount &&
                Objects.equals(battleground, that.battleground);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, playersCount, battleground);
    }

    @Override
    public String toString() {
        return "BattleType{" +
                "id=" + id +
                ", playersCount=" + playersCount +
                ", battleground='" + battleground + '\'' +
                '}';
    }
}
