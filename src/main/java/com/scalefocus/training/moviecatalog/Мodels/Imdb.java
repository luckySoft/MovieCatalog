package com.scalefocus.training.moviecatalog.Мodels;

public class Imdb {


    private String imdb_id;

    private Double rating;

    private Double votes;

    public Imdb(){

    }

    public Imdb(String imdb_id, Double rating, Double votes) {
        this.imdb_id = imdb_id;
        this.rating = rating;
        this.votes = votes;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getVotes() {
        return votes;
    }

    public void setVotes(Double votes) {
        this.votes = votes;
    }
}

