package com.furniture;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Furniture implements Printable {
    private int furnitureId;
    private String name, category, material;
    private double price;
    private int quantity;
    private String status;

    private static final int THRESHOLD = 10;  

    public Furniture(int furnitureId, String name, String category,String material, double price, int quantity) {
        this.furnitureId = furnitureId;
        this.name = name;
        this.category = category;
        this.material = material;
        this.price = price;
        this.quantity = quantity;
        updateStatus();
    }

    private void updateStatus() {
        status = (quantity > 0) ? "AVAILABLE" : "OUT OF STOCK";
    }

   public void checkLowStock(boolean showAlert) {
    if (quantity < THRESHOLD) {

        if (showAlert) {
            System.out.println("ALERT: Low stock for item: " + name +
                    " (ID: " + furnitureId + ") | Remaining: " + quantity);
        }

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/alerts.txt", true));
            pw.println("Item: " + name +
                       ", ID: " + furnitureId +
                       ", Remaining: " + quantity);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error writing alert.");
        }
    }
}

    public int getFurnitureId() { return furnitureId; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getName() { return name; }
    public String getMaterial() { return material; }
    public int getQuantity() { return quantity; }

    public void setPrice(double price) { this.price = price; }

    public void addStock(int qty) {
        quantity += qty;
        updateStatus();
    }

    public void reduceStock(int qty, boolean showAlert) {
        quantity -= qty;
        updateStatus();
        checkLowStock(showAlert);
    }

    public void setStatus(String status) { this.status = status; }

    public void display() {
        System.out.println("ID: " + furnitureId +
                " | Name: " + name +
                " | Category: " + category +
                " | Material: " + material +
                " | Price: Rs." + price +
                " | Quantity: " + quantity +
                " | Status: " + status);
    }
}