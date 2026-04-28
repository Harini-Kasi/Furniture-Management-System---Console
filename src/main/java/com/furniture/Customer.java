package com.furniture;

class Customer extends User {
    private String phone;
    private String address;
    private int rewardPoints;

    public Customer(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.role = "CUSTOMER";
        this.rewardPoints = 0;
    }

    public int getRewardPoints() { return rewardPoints; }
    public void addPoints(int p) { rewardPoints += p; }

    public void display() {
        System.out.println("Customer ID: " + id +
                " | Name: " + name +
                " | Contact: " + phone +
                " | Address: " + address +
                " | Reward Points: " + rewardPoints);
    }
    public String toFileString() {
    return id + "," + name + "," + phone + "," + address + "," + rewardPoints;
    }
}
