package com.scalefocus.training.moviecatalog.Repository;


import com.scalefocus.training.moviecatalog.Models.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Movie findById(ObjectId _id);
}