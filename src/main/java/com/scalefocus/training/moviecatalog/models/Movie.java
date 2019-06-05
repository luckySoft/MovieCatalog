package com.scalefocus.training.moviecatalog.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Movie {

    @Id
    public ObjectId _id;

    private String name;

    private String description;

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public String getDiscription() { return description; }
    public void setDiscription(String discription) { this.description = discription; }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Movie: " + getName() + "description: " + getDiscription();
    }
}
