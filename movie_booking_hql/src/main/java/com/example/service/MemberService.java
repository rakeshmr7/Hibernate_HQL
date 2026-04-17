package com.example.service;

import com.example.dao.MemberDao;
import com.example.dto.Member;

public class MemberService {
    MemberDao dao;
    public MemberService(){
        dao = new MemberDao();
    }
    public void addMember(Member member){
        dao.addMember(member);
    }
    public void showAllMembers(){
        dao.showAllMembers();
    }
    public void searchById(int memberId){
        dao.searchById(memberId);
    }
    public void deleteMember(int memberId){
        dao.deleteMember(memberId);
    }
}

