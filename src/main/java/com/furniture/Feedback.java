package com.furniture;

class Feedback implements Printable {
    int feedbackId;
    String customerName;
    int rating;
    String comment;

    public Feedback(int feedbackId, String customerName, int rating, String comment) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.rating = rating;
        this.comment = comment;
    }

    public void display() {
        System.out.println("Feedback by " + customerName +
                " | Rating: " + rating +
                " | Comment: " + comment);
    }
}