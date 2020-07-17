package com.example.londontourism.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Attraction {

        String Description,imageURL, title;

        public Attraction(){

        }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
