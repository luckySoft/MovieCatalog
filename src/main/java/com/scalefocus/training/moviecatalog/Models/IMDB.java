package com.scalefocus.training.moviecatalog.Models;

public class IMDB {

    private String id;
    private Double rating;
    private Double votes;

    public IMDB(String id, Double rating, Double votes) {
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

    public Double getRating() { return rating; }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getVotes() { return votes; }

    public void setVotes(Double votes) {
        this.votes = votes;
    }
}
