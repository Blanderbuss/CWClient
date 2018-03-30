package com.cw.models.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Artefact {
	@Min(0)
    private int id;
	@NotNull
    private String name;
	@NotNull
    private String type;
    @NotNull
    private int hpBoost;
    @NotNull
    private int manaBoost;
    @NotNull
    private int staminaBoost;
    @NotNull
    @Max(value=500)
    @Min(value=(-500))
    private int hpRegenBoost;
    @NotNull
    @Max(value=500)
    @Min(value=(-500))
    private int manaRegenBoost;
    @NotNull
    private int evasionBoost;
    @NotNull
    private int armorBoost;
    @NotNull
    private String skin;

    public Artefact(String name, String type, int hpBoost, int manaBoost, int staminaBoost, int hpRegenBoost, int manaRegenBoost, int evasionBoost, int armorBoost, String skin) {
        this.name = name;
        this.type = type;
        this.hpBoost = hpBoost;
        this.manaBoost = manaBoost;
        this.staminaBoost = staminaBoost;
        this.hpRegenBoost = hpRegenBoost;
        this.manaRegenBoost = manaRegenBoost;
        this.evasionBoost = evasionBoost;
        this.armorBoost = armorBoost;
        this.skin = skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHpBoost(int hpBoost) {
        this.hpBoost = hpBoost;
    }

    public void setStaminaBoost(int staminaBoost) {
        this.staminaBoost = staminaBoost;
    }

    public void setHpRegenBoost(int hpRegenBoost) {
        this.hpRegenBoost = hpRegenBoost;
    }

    public void setManaRegenBoost(int manaRegenBoost) {
        this.manaRegenBoost = manaRegenBoost;
    }

    public void setEvasionBoost(int evasionBoost) {
        this.evasionBoost = evasionBoost;
    }

    public void setArmorBoost(int armorBoost) {
        this.armorBoost = armorBoost;
    }

    public int getId() {
        return id;
    }

    public int getHpBoost() {
        return hpBoost;
    }

    public int getStaminaBoost() {
        return staminaBoost;
    }

    public int getHpRegenBoost() {
        return hpRegenBoost;
    }

    public int getManaRegenBoost() {
        return manaRegenBoost;
    }

    public int getEvasionBoost() {
        return evasionBoost;
    }

    public int getArmorBoost() {
        return armorBoost;
    }

    public String getSkin() {
        return skin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getManaBoost() {
        return manaBoost;
    }

    public void setManaBoost(int manaBoost) {
        this.manaBoost = manaBoost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
