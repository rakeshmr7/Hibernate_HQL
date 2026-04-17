package com.example.dao;

import org.hibernate.SessionFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.example.dto.Movie;

public class MovieDao {
    SessionFactory factory;
    Session session;
    Transaction tx;

    public MovieDao(){
        factory = new Configuration()
        .configure("hiber.cfg.xml")
        .addAnnotatedClass(Movie.class)
        .buildSessionFactory();
        session = factory.openSession();
    }

    public void addMovie(Movie movie){
        try{
            tx = session.beginTransaction();
            session.save(movie);
            tx.commit();
            System.out.println("Movie added!!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void showAllMovies(){
        try{
            String query = "from Movie";
            Query<Movie> q1 = session.createQuery(query, Movie.class);
            List<Movie> movieList = q1.list();
            if(movieList.isEmpty()){
                System.out.println("No moives streaming!!");
                return;
            }
            movieList.stream().forEach(System.out::println);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void searchById(int id){
        try{
            String query = "from Movie where movieId=:id";
            Query<Movie> q1 = session.createQuery(query, Movie.class);
            q1.setParameter("id", id);
            Movie movie = q1.uniqueResult();
            if(movie != null) System.out.println(movie);
            else System.out.println("Movie not found!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void updatePrice(int id, double price){
        try{
            String query = "update Movie set price=:price where movieId=:id";
            Query<Movie> q1 = session.createQuery(query);
            q1.setParameter("price", price);
            q1.setParameter("id", id);
            tx = session.beginTransaction();
            int row = q1.executeUpdate();
            tx.commit();
            if(row != 0) System.out.println("Movie updated!!");
            else System.out.println("Movie not found!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteMovie(int id){
        try {
            String query = "delete from Movie where movieId=:id";
            Query<Movie> q1 = session.createQuery(query);
            q1.setParameter("id", id);
            tx = session.beginTransaction();
            int row = q1.executeUpdate();
            tx.commit();
            if (row != 0)
                System.out.println("Movie deleted!!");
            else
                System.out.println("Movie not found!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
