package com.scalefocus.training.moviecatalog.exception;

public class MovieNotFoundException extends Exception {

    /**
     * Custom exceptiong which is thrown when a movie is not found
     * @param exception exception message
     */
   public MovieNotFoundException(String exception){
        super( exception );
    }

}
