package com.cw.models.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class Set {
    @NotNull
    private int id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String name;

    @NotNull
    private String code;

    private User user;

    private List<Artefact> artefacts;

    public Set(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Set(String name, String code, User user) {
        this.name = name;
        this.code = code;
        this.user = user;
    }

    public Set(String name, String code, User user, List<Artefact> artefacts) {
        this.name = name;
        this.code = code;
        this.user = user;
        this.artefacts = artefacts;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Artefact> getArtefacts() {
        return artefacts;
    }

    public void setArtefacts(List<Artefact> artefacts) {
        this.artefacts = artefacts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
