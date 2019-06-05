package com.scalefocus.training.moviecatalog.Models;

public class Tomato {

    private String meter;
    private String image;
    private String rating;
    private String reviews;
    private String fresh;
    private String consensus;
    private String userMeter;
    private String userRating;
    private String userReviews;

    public Tomato(String meter, String image, String rating, String reviews, String fresh, String consensus, String userMeter, String userRating, String userReviews) {
        this.meter = meter;
        this.image = image;
        this.rating = rating;
        this.reviews = reviews;
        this.fresh = fresh;
        this.consensus = consensus;
        this.userMeter = userMeter;
        this.userRating = userRating;
        this.userReviews = userReviews;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getFresh() {
        return fresh;
    }

    public void setFresh(String fresh) {
        this.fresh = fresh;
    }

    public String getConsensus() {
        return consensus;
    }

    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }

    public String getUserMeter() {
        return userMeter;
    }

    public void setUserMeter(String userMeter) {
        this.userMeter = userMeter;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(String userReviews) {
        this.userReviews = userReviews;
    }
}
