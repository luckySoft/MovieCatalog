package com.scalefocus.training.moviecatalog.Мodels;

/**
 * POJO class used to stored objects from the database
 */
public class Awards {

    private String wins;
    private Double nominations;
    private String text;


    public Awards(){
    }
    public Awards(String wins, Double nominations, String text) {
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

    public Double getNominations() {
        return nominations;
    }

    public void setNominations(Double nominations) {
        this.nominations = nominations;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
