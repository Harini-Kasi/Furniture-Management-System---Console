package com.furniture;

 class Bill implements Printable {
    int billId;
    Furniture furniture;
    int quantity;
    double total;

    public Bill(int billId, Furniture furniture, int quantity, double total) {
        this.billId = billId;
        this.furniture = furniture;
        this.quantity = quantity;
        this.total = total;
    }

    public void display() {
        System.out.println("Bill ID: " + billId +
                " | Item: " + furniture.getFurnitureId() +
                " | Quantity: " + quantity +
                " | Total: Rs." + total);
    }
    public String toFileString() {
    return billId + "," +
           furniture.getFurnitureId() + "," +
           quantity + "," +
           total;
    }
}

