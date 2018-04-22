package com.cw.BattleLogic;

import com.cw.entities.Artefact;

import java.io.Serializable;
import java.util.List;

public interface FighterI extends Serializable {
    enum Action {
        /**Deals damage to target; drains stamina from actor;
         * If target was in DEFENDING he becomes in FREE
         */
        ATTACK,

        /**Buffs stamina regen, armor, evasion;Puts in DEFENDING
         * Actor and Target must be the same
         */
        DEFEND,

        /**If targer in DEFEND, undoes what does defend
         * Actor and Target must be the same
         */
        FREE,

        /**Deals damage to target; drains mana from actor;
         *
         */
        FIREBALL
    }

    enum Stance {
        FREE,
        DEFENDING
    }

    boolean isAlive();

    String getName();

    int getLvl();

    List<Artefact> getArtefacts();

    int getMaxHp();
    int getMaxMana();
    int getMaxStamina();
    int getMaxSpeed();

    int getCurHp();
    int getCurMana();
    int getCurStamina();
    int getCurSpeed();

    int getRegenHp();
    int getRegenMana();
    int getRegenStamina();

    int getAttack();
    int getEvasion();
    int getArmor();

    Stance getStance();
}
