package com.scalefocus.training.moviecatalog.Models;

public class Awards {

    private String wins;
    private double nominations;
    private String text;

    public Awards(String wins, double nominations, String text) {
        this.wins = wins;
        this.nominations = nominations;
        this.text = text;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public double getNominations() {
        return nominations;
    }

    public void setNominations(double nominations) {
        this.nominations = nominations;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
