package com.furniture;

class Admin extends User {
    public Admin(int id) {
        this.id = id;
        this.role = "ADMIN";
    }

    public void display() {
        System.out.println("Admin ID: " + id);
    }
}