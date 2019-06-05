package com.scalefocus.training.moviecatalog.Models;

public class Tomato {

    private String meter;
    private String image;
    private double rating;
    private double reviews;
    private double fresh;
    private String consensus;
    private double userMeter;
    private double userRating;
    private double userReviews;

    public Tomato(String meter, String image, double rating, double reviews, double fresh, String consensus, double userMeter, double userRating, double userReviews) {
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getReviews() {
        return reviews;
    }

    public void setReviews(double reviews) {
        this.reviews = reviews;
    }

    public double getFresh() {
        return fresh;
    }

    public void setFresh(double fresh) {
        this.fresh = fresh;
    }

    public String getConsensus() {
        return consensus;
    }

    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }

    public double getUserMeter() {
        return userMeter;
    }

    public void setUserMeter(double userMeter) {
        this.userMeter = userMeter;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public double getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(double userReviews) {
        this.userReviews = userReviews;
    }
}
