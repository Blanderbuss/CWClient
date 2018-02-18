package com.cw.models.db;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String login;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_.%+-]{6,30}")
    private String pass;
    @NotNull
    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}")
    private String email;
    @NotNull
    private int experience;
    @NotNull
    @Max(value = 80)
    private int lvl;

    public User() {
    }

    public User(String login, String pass, String email, int experience, int lvl) {
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.experience = experience;
        this.lvl = lvl;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    private void setPass(String pass) {
        this.pass = pass;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setExperience(int experience) {
        this.experience = experience;
    }

    private void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public int getExperience() {
        return experience;
    }

    public int getLvl() {
        return lvl;
    }
}
