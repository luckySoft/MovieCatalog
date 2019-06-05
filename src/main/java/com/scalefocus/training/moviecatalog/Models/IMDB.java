package com.scalefocus.training.moviecatalog.Models;

public class IMDB {

    private String id;
    private String rating;
    private String votes;

    public IMDB(String id, String rating, String votes) {
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }


}
