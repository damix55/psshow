package com.pss.assignment3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "movie")
public class Movie extends Production {

    @Column(name = "director")
    @NotBlank(message = "Director field is mandatory")
    private String director;

    @Column(name = "year")
    @Min(value = 1900)
    @Max(value = 2021)
    private int year;

    @Column(name = "length")
    @Min(value = 10)
    @Max(value = 500)
    private int length;

    public Movie() {
        super();
    }

    public Movie(String title,
                 String genre,
                 String language,
                 String description,
                 String picture,
                 String director,
                 int year,
                 int length) {
        super(title, genre, language, description, picture);
        this.director = director;
        this.year = year;
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}