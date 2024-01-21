package com.pss.assignment3.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Name field is mandatory")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname field is mandatory")
    private String surname;

    @Column(name = "known_as")
    @NotBlank(message = "Known as field is mandatory")
    private String knownAs;

    @Column(name = "gender")
    private int gender;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birthday field is mandatory")
    private Date birthday;

    @Column(name = "picture")
    private String picture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actors_productions",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "production_id", referencedColumnName = "id")
    )
    private final List<Production> productions = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Actor marriedTo;

    public Actor() {
        super();
    }

    public Actor(String name,
                 String surname,
                 String knownAs,
                 int gender,
                 Date birthday,
                 String picture,
                 Actor marriedTo) {
        super();
        this.name = name;
        this.surname = surname;
        this.knownAs = knownAs;
        this.gender = gender;
        this.birthday = birthday;
        this.picture = picture;
        this.marriedTo = marriedTo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getKnownAs() {
        return knownAs;
    }

    public void setKnownAs(String knownAs) {
        this.knownAs = knownAs;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void addProduction(Production production) {
        this.productions.add(production);
    }

    public void deleteProduction(Production production) {
        this.productions.remove(production);
    }

    public Actor getMarriedTo() {
        return marriedTo;
    }

    public void setMarriedTo(Actor marriedTo) {
        this.marriedTo = marriedTo;
    }
}