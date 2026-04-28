package com.furniture;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Inventory {
    ArrayList<Furniture> items = new ArrayList<>();
    HashMap<Integer, Furniture> itemMap = new HashMap<>(); 

    public void addFurniture(Furniture f) {
        items.add(f);
        itemMap.put(f.getFurnitureId(), f); 
        System.out.println("Furniture added to inventory.");
    }

    public Furniture findById(int id) {
        Furniture f = itemMap.get(id);

        if (f == null) {
            System.out.println("ID not found!");
        }

        return f;
    }

    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("Inventory empty.");
            return;
        }

        for (Furniture item : items)
            item.display();
    }

    public void displayByCategory(String cat) {
        boolean found = false;

        for (Furniture item : items) {
            if (item.getCategory().equalsIgnoreCase(cat)) {
                item.display();
                found = true;
            }
        }

        if (!found)
            System.out.println("No furniture found in this category.");
    }
    public void saveToFile() {
    try {
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resources/furniture.txt"));

        for (Furniture f : items) {
            pw.println(f.getFurnitureId() + "," +
                       f.getName() + "," +         
                       f.getCategory() + "," +
                       f.getMaterial() + "," +      
                       f.getPrice() + "," +
                       f.getQuantity());
        }

        pw.close();
        System.out.println("Inventory saved to file.");

    } catch (IOException e) {
        System.out.println("Error saving inventory.");
    }
}
}
