package com.scalefocus.training.moviecatalog.Models;

public class Tomato {

    private String meter;
    private String image;
    private Double rating;
    private Double reviews;
    private Double fresh;
    private String consensus;
    private Double userMeter;
    private Double userRating;
    private Double userReviews;

    public Tomato(String meter, String image, Double rating, Double reviews, Double fresh, String consensus, Double userMeter, Double userRating, Double userReviews) {
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getReviews() {
        return reviews;
    }

    public void setReviews(Double reviews) {
        this.reviews = reviews;
    }

    public Double getFresh() {
        return fresh;
    }

    public void setFresh(Double fresh) {
        this.fresh = fresh;
    }

    public String getConsensus() {
        return consensus;
    }

    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }

    public Double getUserMeter() {
        return userMeter;
    }

    public void setUserMeter(Double userMeter) {
        this.userMeter = userMeter;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public Double getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(Double userReviews) {
        this.userReviews = userReviews;
    }
}
