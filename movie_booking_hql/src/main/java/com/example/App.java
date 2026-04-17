package com.example;

import java.util.Scanner;

import com.example.dto.Member;
import com.example.dto.Movie;
import com.example.service.BookService;
import com.example.service.MemberService;
import com.example.service.MovieService;


public class App 
{
    public static void main( String[] args )
    {
        Scanner s = new Scanner(System.in);
        MemberService memberService = new MemberService();
        MovieService movieService = new MovieService();
        BookService bookService = new BookService();
        while(true){
            System.out.println("Movie Operations:");
            System.out.println("1. Add Movie\n" +
                                "2. Show All Movies\n" + 
                                "3. Search Movie by Id\n" +
                                "4. Update Price\n" +
                                "5. Delete Movie");
            System.out.println("\nMember Operations:");
            System.out.println("6. Add Member\n" +
                                "7. Show All Members\n" +
                                "8. Search Member by Id\n" +
                                "9. Delete Member");
            System.out.println("\nBooking Operations:");
            System.out.println("10. Book Ticket\n" +
                                "11. Cancel Ticket\n" +
                                "12. Show Booking Details\n" +
                              "13. Total Amount\n" +
                                "14. Exit");
            System.out.println("Enter your choice: ");
            int choice = s.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter Movie name: ");
                    s.nextLine();
                    String name = s.nextLine();
                    System.out.println("Enter price: ");
                    double price = s.nextDouble();
                    System.out.println("Enter seats: ");
                    int seats = s.nextInt();
                    Movie m = new Movie(name, price, seats);
                    movieService.addMoive(m);
                    break;
                case 2:
                    movieService.showAllMovies();
                    break;
                case 3:
                    System.out.println("Enter movie id: ");
                    int movieId = s.nextInt();
                    movieService.searchById(movieId);
                    break;
                case 4:
                    System.out.println("Enter movie id: ");
                    movieId = s.nextInt();
                    System.out.println("Enter price: ");
                    price = s.nextDouble();
                    movieService.updatePrice(movieId, price);
                    break;
                case 5:
                    System.out.println("Enter movie id: ");
                    movieId = s.nextInt();
                    movieService.deleteMovie(movieId);
                    break;
                case 6:
                    System.out.println("Name: ");
                    s.nextLine();
                    name = s.nextLine();
                    memberService.addMember(new Member(name, 0, 0));
                    break;
                case 7:
                    memberService.showAllMembers();
                    break;
                case 8:
                    System.out.println("Enter member id: ");
                    int memberId= s.nextInt();
                    memberService.searchById(memberId);
                    break;
                case 9:
                    System.out.println("Enter member id: ");
                    memberId = s.nextInt();
                    memberService.deleteMember(memberId);
                    break;
                case 10:
                    System.out.println("Enter member id: ");
                    memberId = s.nextInt();
                    bookService.bookTicket(memberId);
                    break;
                case 11:
                    System.out.println("Enter member id: ");
                    memberId = s.nextInt();
                    bookService.cancelTicket(memberId);
                    break;
                case 12:
                    System.out.println("Enter member id: ");
                    memberId = s.nextInt();
                    bookService.showBookingDetails(memberId);
                    break;
                case 13:
                    System.out.println("Enter member id: ");
                    memberId = s.nextInt();
                    bookService.totalAmount(memberId);
                    break;
                case 14:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid input!!");
            }
        }
    }
}
