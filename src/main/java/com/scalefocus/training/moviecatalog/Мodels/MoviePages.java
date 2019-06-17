package com.scalefocus.training.moviecatalog.Мodels;

import java.util.List;

/**
 * Simple POJO class
 * @author Mariyan Topalov
 * @author Zdravko Karamanolov
 */
public class MoviePages {

    private Long numberOfPages;

    private List<Movie> movieList;

    public MoviePages(Long pages, List<Movie> movieList) {
        this.numberOfPages = pages;
        this.movieList = movieList;

    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;

    }
}
