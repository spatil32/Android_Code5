package com.spatil32.a20367073_bookreviews;

/**
 * Created by Shreyas on 11/5/2016.
 */
public class Book {
    //private class members
    private int id;
    private String title;
    private String author;
    private Float rating;

    //parameterless ctor
    public Book(){}

    //parameterized ctor
    public Book(String title, String author, Float rating)
    {
        super();
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    //getters
    public int getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAuthor() {
        return this.author;
    }
    public Float getRating() {  return this.rating; }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }

    //toString()
    @Override
    public String toString() {
        return "Book [id = " + getId() + ", title = " + getTitle() + ", author = " + getAuthor() + ", rating = " + getRating() + "]";
    }
}