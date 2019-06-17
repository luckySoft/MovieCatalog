package com.scalefocus.training.moviecatalog.controller.advices;


import com.scalefocus.training.moviecatalog.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ControllerAdvice class used to handle HTTP Not_found(404) status
 * @author Mariyan Topalov
 */
@ControllerAdvice
class MovieNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String movieNotFoundHandler(MovieNotFoundException ex) {
        return ex.getMessage();
    }


}
