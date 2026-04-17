package com.example.service;

import com.example.dao.MovieDao;
import com.example.dto.Movie;

public class MovieService {
    MovieDao dao;
    public MovieService(){
        dao = new MovieDao();
    }
    public void addMoive(Movie movie){
        dao.addMovie(movie);
    }
    public void showAllMovies(){
        dao.showAllMovies();
    }
    public void searchById(int movieId){
        dao.searchById(movieId);
    }
    public void updatePrice(int movieId, double price){
        dao.updatePrice(movieId, price);
    }
    public void deleteMovie(int movieId){
        dao.deleteMovie(movieId);
    }
}
