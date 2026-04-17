package com.example.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
    private String memberName;
    private int movieId;
    private int tickets;
    public Member(int memberId, String memberName, int movieId, int tickets) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.movieId = movieId;
        this.tickets = tickets;
    }
    public Member(String memberName, int movieId, int tickets) {
        this.memberName = memberName;
        this.movieId = movieId;
        this.tickets = tickets;
    }
    public Member() {
    }
    public int getMemberId() {
        return memberId;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    public int getTickets() {
        return tickets;
    }
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
    @Override
    public String toString() {
        return "Member [memberId=" + memberId + ", memberName=" + memberName + ", movieID=" + movieId + ", tickets="
                + tickets + "]";
    }
    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    
}
