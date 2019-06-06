package com.scalefocus.training.moviecatalog.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movieDetails")
public class Movie {

    @Id
    private String id;

    private String title;

    private Double year;

    private String[] countries;

    private String[] genres;

    private String director;

    private String[] writers;

    private String[] actors;

    private String plot;

    private String poster;


    private Imdb imdb;

    private Tomato tomato;

    private Awards awards;

    private String type;

    private String rated;



    public Movie(String id, String title, double year, String[] countries, String[] genres, String director, String[] writers, String[] actors, String plot, String poster, Imdb imdb, Tomato tomato, Awards awards, String type, String rated) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.countries = countries;
        this.genres = genres;
        this.director = director;
        this.writers = writers;
        this.actors = actors;
        this.plot = plot;
        this.poster = poster;
        this.imdb = imdb;
        this.tomato = tomato;
        this.awards = awards;
        this.type = type;
        this.rated = rated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title  = title;
    }


    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Imdb getImdb() {
        return imdb;
    }

    public void setImdb(Imdb imdb) {
        this.imdb = imdb;
    }

    public Tomato getTomato() {
        return tomato;
    }

    public void setTomato(Tomato tomato) {
        this.tomato = tomato;
    }

    public Awards getAwards() {
        return awards;
    }

    public void setAwards(Awards awards) {
        this.awards = awards;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }
}

