package com.example.londontourism.Model;


public class Review {

    String review_description, added_by;
    Long stars;
    public Review() {

    }
    public Review( String review_description, String added_by, Long stars){
        this.review_description = review_description;
        this.added_by = added_by;
        this.stars = stars;
    }



    public String getReview_description() {
        return review_description;
    }

    public void setReview_description(String review_description) {
        this.review_description = review_description;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }
}
