package com.furniture;

class Staff extends User {
    public Staff(int id, String name) {
        this.id = id;
        this.name = name;
        this.role = "STAFF";
    }

    public void display() {
        System.out.println("Staff ID: " + id + " | Name: " + name);
    }
}