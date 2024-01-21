package com.pss.assignment3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Production {

    protected String type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    @NotBlank(message = "Title field is mandatory")
    private String title;

    @Column(name = "genre")
    @NotBlank(message = "Genre field is mandatory")
    private String genre;

    @Column(name = "language")
    @NotBlank(message = "Language field is mandatory")
    private String language;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotBlank(message = "Description field is mandatory")
    private String description;

    @Column(name = "picture")
    private String picture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actors_productions",
            joinColumns = @JoinColumn(name = "production_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id")
    )
    private List<Actor> actors = new ArrayList<>();

    public Production() {
        super();
    }

    public Production(String title, String genre, String language, String description, String picture) {
        super();
        this.title = title;
        this.genre = genre;
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getType() {
        if (this.getClass().getSimpleName().equals("Series")) {
            return "series";
        }
        return "movies";
    }
}