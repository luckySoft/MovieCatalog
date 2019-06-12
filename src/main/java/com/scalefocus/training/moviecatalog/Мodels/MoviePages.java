package com.scalefocus.training.moviecatalog.Мodels;

import java.util.List;

public class MoviePages {

    private Long pages;
    private List<Movie> movieList;

    public MoviePages(Long pages, List<Movie> movieList) {
        this.pages = pages;
        this.movieList = movieList;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
