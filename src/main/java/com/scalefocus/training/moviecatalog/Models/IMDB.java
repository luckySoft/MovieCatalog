package com.scalefocus.training.moviecatalog.Models;

public class IMDB {

    private String id;
    private double rating;
    private double votes;

    public IMDB(String id, double rating, double votes) {
        this.id = id;
        this.rating = rating;
        this.votes = votes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getVotes() {
        return votes;
    }

    public void setVotes(double votes) {
        this.votes = votes;
    }
}
