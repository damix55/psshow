package com.pss.assignment3.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "series")
public class Series extends Production {

    @Column(name = "finished")
    @NotNull(message = "Finished field is mandatory")
    private boolean finished;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Season> seasons = new ArrayList<>();

    public Series() {
        super();
    }

    public Series(String title,
                  String genre,
                  String language,
                  String description,
                  String picture,
                  boolean finished) {
        super(title, genre, language, description, picture);
        this.finished = finished;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<Season> getSeasons() {
        List<Season> seasons = this.seasons;
        Collections.sort(seasons);
        return seasons;
    }

    public void addSeasons(Season season) {
        this.seasons.add(season);
        season.setSeries(this);
    }

    public String getYears() {
        seasons = getSeasons();

        if (seasons.size() == 0) {
            return "–";
        }

        if (this.finished) {
            return seasons.get(0).getYear() + " – " + seasons.get(seasons.size() - 1).getYear();
        }
        return seasons.get(0).getYear() + " – present";
    }
}