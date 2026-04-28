package com.furniture;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FurnitureManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Bill> bills = new ArrayList<>();
        ArrayList<Feedback> feedbacks = new ArrayList<>();

        int mainChoice;

        do {
            System.out.println("\n===== FURNITURE MANAGEMENT SYSTEM =====");
            System.out.println("1. Admin");
            System.out.println("2. Staff");
            System.out.println("3. Customer");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            mainChoice = sc.nextInt();

            switch (mainChoice) {

                /* ----------- ADMIN MENU----------- */
                case 1: {
                    System.out.print("Enter Admin ID: ");
                    Admin admin = new Admin(sc.nextInt());

                    int ch;
                    do {
                        System.out.println("\n--- ADMIN MENU ---");
                        System.out.println("1. Add Furniture");
                        System.out.println("2. Update Price");
                        System.out.println("3. Update Stock");
                        System.out.println("4. Change Furniture Status");
                        System.out.println("5. View Inventory");
                        System.out.println("6. View Sales Summary");
                        System.out.println("7. Logout");
                        ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                System.out.print("ID: ");
                                int id = sc.nextInt(); sc.nextLine();
                                System.out.print("Name: ");
                                String n = sc.nextLine();
                                System.out.print("Category: ");
                                String c = sc.nextLine();
                                System.out.print("Material: ");
                                String m = sc.nextLine();
                                System.out.print("Price: ");
                                double p = sc.nextDouble();
                                System.out.print("Quantity: ");
                                int q = sc.nextInt();
                                inventory.addFurniture(new Furniture(id, n, c, m, p, q));
                                break;

                            case 2:
                                System.out.print("Furniture ID: ");
                                int pid = sc.nextInt();
                                Furniture fp = inventory.findById(pid);
                                if (fp != null) {
                                    System.out.print("New Price: ");
                                    fp.setPrice(sc.nextDouble());
                                }
                                break;

                            case 3:
                                System.out.print("Furniture ID: ");
                                int sid = sc.nextInt();
                                Furniture fs = inventory.findById(sid);
                                if (fs != null) {
                                    System.out.print("Add Quantity: ");
                                    fs.addStock(sc.nextInt());
                                }
                                break;

                            case 4:
                                System.out.print("Furniture ID: ");
                                int stid = sc.nextInt(); sc.nextLine();
                                Furniture fst = inventory.findById(stid);
                                if (fst != null) {
                                    System.out.print("Enter Status: ");
                                    fst.setStatus(sc.nextLine());
                                }
                                break;

                            case 5:
                                inventory.displayAll();
                                break;

                            case 6:
                                System.out.println("Total Bills Generated: " + bills.size());
                                break;
                        }
                    } while (ch != 7);
                    break;
                }

                /* ----------- STAFF MENU----------- */
                case 2: {
                    System.out.print("Enter Staff ID: ");
                    int sid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Staff Name: ");
                    Staff staff = new Staff(sid, sc.nextLine());

                    int ch;
                    do {
                        System.out.println("\n--- STAFF MENU ---");
                        System.out.println("1. View Inventory");
                        System.out.println("2. Search by Category");
                        System.out.println("3. Assist Purchase");
                        System.out.println("4. Generate Bill");
                        System.out.println("5. Check Stock Status");
                        System.out.println("6. Logout");
                        ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                inventory.displayAll();
                                break;
                            case 2:
                                sc.nextLine();
                                System.out.print("Category: ");
                                inventory.displayByCategory(sc.nextLine());
                                break;
                            case 3:
                                System.out.println("Assisting customer with purchase...");
                                break;
                            case 4:
                                System.out.print("Furniture ID: ");
                                int fid = sc.nextInt();
                                System.out.print("Quantity: ");
                                int qty = sc.nextInt();
                                Furniture f = inventory.findById(fid);
                                if (f != null && f.getQuantity() >= qty) {
                                    double total = f.getPrice() * qty;
                                    new Payment().process(total);
                                    f.reduceStock(qty, true);  
                                    bills.add(new Bill(bills.size() + 1, f, qty, total));
                                }
                                break;
                            case 5:
                                inventory.displayAll();
                                break;
                        }
                    } while (ch != 6);
                    break;
                }

                /* ----------- CUSTOMER MENU----------- */
                case 3: {
                    System.out.print("Enter Customer ID: ");
                    int cid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String addr = sc.nextLine();

                    Customer customer = new Customer(cid, cname, phone, addr);

                    int ch;
                    do {
                        System.out.println("\n--- CUSTOMER MENU ---");
                        System.out.println("1. Browse Furniture");
                        System.out.println("2. Filter by Category");
                        System.out.println("3. Buy Furniture");
                        System.out.println("4. View Bill History");
                        System.out.println("5. Reward Points");
                        System.out.println("6. Give Feedback");
                        System.out.println("7. Logout");
                        ch = sc.nextInt();

                        switch (ch) {
                            case 1:
                                inventory.displayAll();
                                break;
                            case 2:
                                sc.nextLine();
                                System.out.print("Category: ");
                                inventory.displayByCategory(sc.nextLine());
                                break;
                            case 3:
                                customers.add(customer);  
                                System.out.print("Furniture ID: ");
                                int fid = sc.nextInt();
                                System.out.print("Quantity: ");
                                int qty = sc.nextInt();
                                Furniture f = inventory.findById(fid);
                                if (f != null && f.getQuantity() >= qty) {
                                    double total = f.getPrice() * qty;
                                    if (customer.getRewardPoints() >= 100)
                                        total *= 0.9;
                                    new Payment().process(total);
                                    f.reduceStock(qty, true); 
                                    customer.addPoints(20);
                                    bills.add(new Bill(bills.size() + 1, f, qty, total));
                                }
                                break;
                            case 4:
                                for (Bill b : bills)
                                    b.display();
                                break;
                            case 5:
                                customer.display();
                                break;
                            case 6:
                                sc.nextLine();
                                System.out.print("Rating: ");
                                int r = sc.nextInt(); sc.nextLine();
                                System.out.print("Comment: ");
                                String cm = sc.nextLine();
                                feedbacks.add(new Feedback(feedbacks.size() + 1, customer.name, r, cm));
                                break;
                        }
                    } while (ch != 7);
                    break;
                }
            }
        } while (mainChoice != 4);
        inventory.saveToFile();
        saveBills(bills);
        saveCustomers(customers);   
System.out.println("System closed.Thank You!");
    }
    public static void saveBills(ArrayList<Bill> bills) {
    try {
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/bills.txt"));

        for (Bill b : bills) {
            pw.println(b.toFileString());
        }

        pw.close();
        System.out.println("Bills saved to file.");

    } catch (IOException e) {
        System.out.println("Error saving bills.");
    }
  }
  public static void saveCustomers(ArrayList<Customer> customers) {
    try {
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/customers.txt"));

        for (Customer c : customers) {
            pw.println(c.toFileString());
        }

        pw.close();
        System.out.println("Customers saved to file.");

    } catch (IOException e) {
        System.out.println("Error saving customers.");
    }
  }
}