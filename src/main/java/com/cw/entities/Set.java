package com.cw.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Set implements Serializable {
    @NotNull
    private int id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String name;

    @NotNull
    private String code;

    private User user;

    private List<Artefact> artefacts = new LinkedList<>();

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

    // beware: this constructor does not set user field
    // because safe copying of new User(other.user) in some cases causes infinite loop
    // to set user field use setter instead
    public Set(Set other) {
        this.id = other.id;
        this.name = other.name;
        this.code = other.code;
        this.artefacts = other.artefacts.stream()
                .map(Artefact::new)
                .collect(Collectors.toList());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return id == set.id &&
                Objects.equals(name, set.name) &&
                Objects.equals(code, set.code) &&
                Objects.equals(user.getId(), set.user.getId()) &&
                Objects.equals(artefacts, set.artefacts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, code, user.getId(), artefacts);
    }

    @Override
    public String toString() {
        return "Set{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
      //          ", user=" + user.getEmail() +
                ", artefacts=" + artefacts +
                '}';
    }
}
