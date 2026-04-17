package com.example.service;

import com.example.dao.BookDao;

public class BookService {
    BookDao dao;
    public BookService(){
        dao = new BookDao();
    }
    public void bookTicket(int memberId){
        dao.bookTicket(memberId);
    }
    public void cancelTicket(int memberId){
        dao.cancelTicket(memberId);
    }
    public void showBookingDetails(int memberId){
        dao.showBookingDetails(memberId);
    }
    public void totalAmount(int memberId){
        dao.totalAmount(memberId);
    }
}
