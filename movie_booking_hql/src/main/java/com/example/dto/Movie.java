package com.example.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private String movieName;
    private double price;
    private int seats;
    public Movie(int movieId, String movieName, double price, int seats) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.price = price;
        this.seats = seats;
    }
    public Movie(String movieName, double price, int seats) {
        this.movieName = movieName;
        this.price = price;
        this.seats = seats;
    }
    public Movie() {
    }
    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    @Override
    public String toString() {
        return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", price=" + price + ", seats=" + seats + "]";
    }
    
}
