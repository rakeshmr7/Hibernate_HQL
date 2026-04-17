package com.example.dao;

import com.example.dto.Member;
import com.example.dto.Movie;
import org.hibernate.SessionFactory;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class BookDao {
    SessionFactory factory;
    Session session;
    Transaction tx;
    MovieDao movieDao;
    Scanner s;

    public BookDao(){
        factory = new Configuration()
        .configure("hiber.cfg.xml")
        .addAnnotatedClass(Member.class)
        .addAnnotatedClass(Movie.class)
        .buildSessionFactory();
        session = factory.openSession();
        movieDao = new MovieDao();
        s = new Scanner(System.in);
    }

    public void bookTicket(int memberId){
        try{
            Member member = session.get(Member.class, memberId);
            if(member == null){
                System.out.println("Member not found!!"); return;
            }
            System.out.println("Movies Streaming:");
            movieDao.showAllMovies();
            System.out.println("Enter movieId: ");
            int movieId = s.nextInt();
            System.out.println("Enter no.of tickets:" );
            int tickets = s.nextInt();
            Movie movie = session.get(Movie.class, movieId);
            if (movie == null) {
                System.out.println("Movie not found!!");
                return;
            }
            if (tickets > movie.getSeats() || movie.getSeats() == 0) {
                System.out.println("Seat not available!!\nAvailable Seats for this movie: " + movie.getSeats());
                return;
            }
            tx = session.beginTransaction();
            String query = "update Member set movieId=:movieId, tickets=:tickets where memberId=:memberId";
            Query q = session.createQuery(query);
            q.setParameter("movieId", movieId);
            q.setParameter("tickets", tickets);
            q.setParameter("memberId", memberId);
            int row = q.executeUpdate();
            query = "update Movie set seats=:seats where movieId=:movieId";
            q = session.createQuery(query);
            q.setParameter("seats", movie.getSeats() - tickets);
            q.setParameter("movieId", movieId);
            int row2 = q.executeUpdate();
            tx.commit();
            session.refresh(member);
            session.refresh(movie);
            if(row == 1 && row2 == 1) System.out.println("Booking success!!");
            else System.out.println("Booking failed!!");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void cancelTicket(int memberId){
        try{
            Member member = session.get(Member.class, memberId);
            if(member == null){
                System.out.println("Member not found!!");
                return;
            }
            if(member.getMovieId() == 0){
                System.out.println("You didn't book any movie!!");
                return;
            }
            System.out.println("Your movie id: " + member.getMovieId() + "\nPress enter to cancel");
            s.nextLine();
            String query = "update Member set movieId = 0, tickets = 0 where memberId=:memberId";
            tx = session.beginTransaction();
            Query q = session.createQuery(query);
            q.setParameter("memberId", memberId);
            int row = q.executeUpdate();
            query = "update Movie set seats=:seats where movieId=:movieId";
            q = session.createQuery(query);
            Movie movie = session.get(Movie.class, member.getMovieId());
            q.setParameter("seats", movie.getSeats() + member.getTickets());
            q.setParameter("movieId", movie.getMovieId());
            int row2 = q.executeUpdate();
            tx.commit();
            if (row == 1 && row2 == 1)
                System.out.println("Cancellation success!!\nRefund amount: " + movie.getPrice() * member.getTickets());
            else
                System.out.println("Cancellation failed!!");
            session.refresh(member);
            session.refresh(movie);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void showBookingDetails(int memberId){
        try{
            String query = "from Member where memberId=:memberId";
            Query<Member> q = session.createQuery(query, Member.class);
            q.setParameter("memberId", memberId);
            Member member = q.uniqueResult();
            if(member == null) {
                System.out.println("Member not found!!");
                return;
            }
            if(member.getMovieId() == 0){
                System.out.println("You have no booking!!");
                return;
            }
            query = "from Movie where movieId=:movieId";
            Query<Movie> q2 = session.createQuery(query, Movie.class);
            q2.setParameter("movieId", member.getMovieId());
            Movie movie = q2.uniqueResult();
            System.out.println("Your booking details:\nMovie Id: " + member.getMovieId()
                    + "\nNo of tickets: " + member.getTickets()
                + "\nTotal Amount: " + member.getTickets() * movie.getPrice());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void totalAmount(int memberId){
        try{
            String query = "from Member where memberId=:memberId";
            Query<Member> q = session.createQuery(query, Member.class);
            q.setParameter("memberId", memberId);
            Member member = q.uniqueResult();
            if (member == null) {
                System.out.println("Member not found!!");
                return;
            }
            if (member.getMovieId() == 0) {
                System.out.println("You have no booking!!");
                return;
            }
            query = "from Movie where movieId=:movieId";
            Query<Movie> q2 = session.createQuery(query, Movie.class);
            q2.setParameter("movieId", member.getMovieId());
            Movie movie = q2.uniqueResult();
            System.out.println("Ticket\tPrice\tTotal Amount");
            System.out.println(member.getTickets() + "\t" + movie.getPrice() + "\t" + movie.getPrice() * member.getTickets());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
