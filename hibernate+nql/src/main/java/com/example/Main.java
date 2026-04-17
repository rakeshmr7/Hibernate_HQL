package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        AppDao dao = new AppDao();
        while(true){
            System.out.println("1. Add Product");
            System.out.println("2. Search Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Update Product");
            //Using HQL
            System.out.println("5. Show all Products");
            System.out.println("6. Select by id");
            System.out.println("7. Delete by id");
            System.out.println("8. Update by id");
            System.out.println("9. Display in order by high price");
            System.out.println("10. Pagination");
            System.out.println("11. Print Page");
            System.out.println("12. Exit...");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = s.nextInt();
                    s.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = s.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = s.nextDouble();
                    dao.add(new Product(id, name, price));
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    id = s.nextInt();
                    dao.search(id);
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    id = s.nextInt();
                    dao.delete(id);
                    break;
                case 4:
                    System.out.print("Enter Product ID: ");
                    id = s.nextInt();
                    s.nextLine();
                    System.out.print("Enter Product Price: ");
                    price = s.nextDouble();
                    dao.update(id, price);
                    break;
                case 5:
                    dao.selectAll();
                    break;
                case 6:
                    System.out.println("Enter product id: ");
                    id = s.nextInt();
                    dao.selectById(id);
                    break;
                case 7:
                    System.out.println("Enter product id: ");
                    id = s.nextInt();
                    dao.deleteById(id);
                    break;
                case 8:
                    System.out.print("Enter Product ID: ");
                    id = s.nextInt();
                    s.nextLine();
                    System.out.print("Enter Product Price: ");
                    price = s.nextDouble();
                    dao.updateByID(id, price);
                    break;
                case 9:
                    dao.displayOrderByPrice();
                    break;
                case 10:
                    dao.pagination();
                    break;
                case 11:
                    System.out.println("Enter page no: ");
                    int pagNo = s.nextInt();
                    dao.printPage(pagNo);
                    break;
                case 12:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
