package com.pss.assignment3.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "season")
public class Season implements Comparable<Season> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "number")
    @Min(value = 1)
    @Max(value = 200)
    private int number;

    @Column(name = "year")
    @Min(value = 1900)
    @Max(value = 2021)
    private int year;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private final List<Episode> episodes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    public Season() {
    }

    public Season(int number,
                  int year) {
        this.number = number;
        this.year = year;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public List<Episode> getEpisodes() {
        List<Episode> episodes = this.episodes;
        Collections.sort(episodes);
        return episodes;
    }

    public Series getSeries() {
        return this.series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public void addEpisodes(Episode episode) {
        this.episodes.add(episode);
        episode.setSeason(this);
    }

    @Override
    public int compareTo(Season s) {
        Integer number = this.getNumber();
        return number.compareTo(s.getNumber());
    }
}
