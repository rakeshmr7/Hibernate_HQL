package com.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Hello world!
 *
 */
public class AppDao {
    SessionFactory factory;
    Session session;
    Transaction tx;

    public AppDao() {
        this.factory = new Configuration().configure("hiber.cfg.xml").addAnnotatedClass(Product.class).buildSessionFactory();
        this.session = factory.openSession();
    }

    public void add(Product p) {
        try {
            tx = session.beginTransaction();
            session.save(p);
            tx.commit();
            System.out.println("Product Added Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Native Query
        // try{
        //     tx = session.beginTransaction();
        //     String query = "insert into products(id, name, price) value(?, ?, ?);";
        //     Query q = session.createNativeQuery(query);
        //     q.setParameter(1, p.getId());
        //     q.setParameter(2, p.getName());
        //     q.setParameter(3, p.getPrice());
        //     int row = q.executeUpdate();
        //     tx.commit();
        //     if (row != 0)
        //         System.out.println("Insert successful");
        //     else
        //         System.out.println("Insert unsuccessful");

        // }catch(Exception e){
        //     System.out.println(e.getMessage());
        // }
    }

    public void search(int id) {
        try {
            Product p = session.find(Product.class, id);
            if (p != null) {
                System.out.println("Product Found: " + p.getName() + " - " + p.getPrice());
            } else {
                System.out.println("Not Found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            tx = session.beginTransaction();
            Product p = session.find(Product.class, id);
            if (p != null) {
                session.delete(p);
                tx.commit();
                System.out.println("Product Deleted Successfully");
            } else {
                System.out.println("Not Found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, double price) {
        try {
            tx = session.beginTransaction();
            Product p = session.get(Product.class, id);
            if (p != null) {
                p.setPrice(price);
                session.update(p);
                tx.commit();
            } else {
                System.out.println("Not Found");
            }
            System.out.println("Product Updated Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //HQL
    public void selectAll(){
        String query = "from Product";
        Query<Product> q1 = session.createQuery(query, Product.class);
        List<Product> productList = q1.list();
        for(Product product : productList) {
            System.out.println(product);
        }

        //Native Query
        // String query = "select * from products";
        // Query<Product> q1 = session.createNativeQuery(query, Product.class);
        // List<Product> productList = q1.list();
        // for(Product product : productList) {
        //     System.out.println(product);
        // }
    }

    public void selectById(int idVar){
        String query = "from Product where id=: idvar";
        Query<Product> q1 = session.createQuery(query, Product.class);
        q1.setParameter("idvar", idVar);
        Product p = q1.uniqueResult();//If we know only one row will be the result use this, safe from null
        if(p != null) System.out.println(p);
        else System.out.println("Product not found!!");

        //Native Query
        // String query = "select * from products where id = ?";
        // Query<Product> q1 = session.createNativeQuery(query, Product.class);
        // q1.setParameter(1, idVar);
        // Product p = q1.uniqueResult();
        // if (p != null)
        //     System.out.println(p);
        // else
        //     System.out.println("Product not found!!");
    }

    public void deleteById(int idVar) {
        String query = "delete from Product where id=: idvar";
        Query q1 = session.createQuery(query);
        q1.setParameter("idvar", idVar);
        tx = session.beginTransaction();
        int row = q1.executeUpdate();
        tx.commit();
        if(row != 0) System.out.println("Delete successful");
        else System.out.println("Product not found!!");

        //Native Query
        // String query = "delete from products where id=?";
        // Query q1 = session.createNativeQuery(query);
        // q1.setParameter(1, idVar);
        // tx = session.beginTransaction();
        // int row = q1.executeUpdate();
        // tx.commit();
        // if (row != 0)
        //     System.out.println("Delete successful");
        // else
        //     System.out.println("Product not found!!");
    }

    public void updateByID(int id, double price){
        String query = "update Product set price=:price where id=:id";
        Query q1 = session.createQuery(query);
        q1.setParameter("price", price);
        q1.setParameter("id", id);
        tx = session.beginTransaction();
        int row = q1.executeUpdate();
        tx.commit();
        if (row != 0)
            System.out.println("Update successful");
        else
            System.out.println("Product not found!!");

        // Native Query
        // String query = "update products set price=? where id=?";
        // Query q1 = session.createNativeQuery(query);
        // q1.setParameter(1, price);
        // q1.setParameter(2, id);
        // tx = session.beginTransaction();
        // int row = q1.executeUpdate();
        // tx.commit();
        // if (row != 0)
        // System.out.println("Update successful");
        // else
        // System.out.println("Product not found!!");
    }
    public void displayOrderByPrice(){
        String query = "from Product order by price desc";
        Query<Product> q1 = session.createQuery(query, Product.class);
        List<Product> productList = q1.list();
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void pagination(){
        String query = "from Product";
        Query<Product> q1 = session.createQuery(query, Product.class);
        q1.setFirstResult(2);//start psotion - skip first 2 rows
        q1.setMaxResults(3);//max rows
        List<Product> productList = q1.list();
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public void printPage(int pageNo){
        int pageSize = 3;
        String query = "from Product";
        Query<Product> q1 = session.createQuery(query, Product.class);
        q1.setFirstResult((pageNo - 1) * pageSize);// start psotion - skip first 2 rows
        q1.setMaxResults(pageSize);// max rows
        List<Product> productList = q1.list();
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}