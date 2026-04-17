package com.example.dao;

import org.hibernate.SessionFactory;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.example.dto.Member;


public class MemberDao {
    SessionFactory factory;
    Session session;
    Transaction tx;

    public MemberDao(){
        factory = new Configuration()
        .configure("hiber.cfg.xml")
        .addAnnotatedClass(Member.class)
        .buildSessionFactory();
        session = factory.openSession();
    }

    public void addMember(Member member){
        try{
            tx = session.beginTransaction();
            session.save(member);
            tx.commit();
            System.out.println("Member added!!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void showAllMembers(){
        try{
            String query = "from Member";
            Query<Member> q1 = session.createQuery(query, Member.class);
            List<Member> memberList = q1.list();
            if(memberList.isEmpty()){
                System.out.println("No member added!!");
                return;
            }
            memberList.stream().forEach(System.out::println);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void searchById(int id){
        try{
            String query = "from Member where memberId=:id";
            Query<Member> q1 = session.createQuery(query, Member.class);
            q1.setParameter("id", id);
            Member member = q1.uniqueResult();
            if(member != null) System.out.println(member);
            else System.out.println("Member not found!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteMember(int id){
        try {
            String query = "delete from Member where memberId=:id";
            Query<Member> q1 = session.createQuery(query);
            q1.setParameter("id", id);
            tx = session.beginTransaction();
            int row = q1.executeUpdate();
            tx.commit();
            if (row != 0)
                System.out.println("Member deleted!!");
            else
                System.out.println("Member not found!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
}
