package com.example.londontourism.Model;

public class Review {
    String author, review_added, comment;
    Integer stars;

    public Review(String author, String review_added, Integer stars, String comment){
        this.author = author;
        this.review_added = review_added;
        this.stars = stars;
        this.comment = comment;
    }

    public Review(){

    }

    public String getAuthor() {
        return author;
    }

    public String getReview_added() {
        return review_added;
    }

    public Integer getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setReview_added(String review_added) {
        this.review_added = review_added;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
