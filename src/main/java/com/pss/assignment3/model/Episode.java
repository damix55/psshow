package com.pss.assignment3.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "episode")
public class Episode implements Comparable<Episode> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "number")
    @Min(value = 1)
    @Max(value = 500)
    private int number;

    @Column(name = "title")
    @NotBlank(message = "Title field is mandatory")
    private String title;

    @Column(name = "length")
    @Min(value = 1)
    @Max(value = 500)
    private int length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;

    public Episode() {
    }

    public Episode(int number,
                   String title,
                   int length) {
        this.number = number;
        this.title = title;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Season getSeason() {
        return this.season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    @Override
    public int compareTo(Episode e) {
        Integer number = this.getNumber();
        return number.compareTo(e.getNumber());
    }
}