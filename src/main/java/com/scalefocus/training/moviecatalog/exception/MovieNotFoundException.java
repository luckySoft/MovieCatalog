package com.scalefocus.training.moviecatalog.exception;

public class MovieNotFoundException extends Exception {

   public MovieNotFoundException(String exception){
        super("No movie with ID " + exception + " is present!");
    }

}
