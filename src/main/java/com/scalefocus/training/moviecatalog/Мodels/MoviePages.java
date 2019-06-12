package com.scalefocus.training.moviecatalog.Мodels;

import java.util.List;

public class MoviePages {

    private Long pages;
    private List<Movie> movies;

    public MoviePages(Long pages, List<Movie> movies) {
        this.pages = pages;
        this.movies = movies;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
