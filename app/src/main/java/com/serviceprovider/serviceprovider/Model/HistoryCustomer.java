package com.serviceprovider.serviceprovider.Model;

public class HistoryCustomer {
    String title;
    String description;
    String date;
    String price;
    String taskerPerson;

    public HistoryCustomer() {
    }

    public HistoryCustomer(String title, String description, String date, String price, String taskerPerson) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.price = price;
        this.taskerPerson = taskerPerson;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getTaskerPerson() {
        return taskerPerson;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTaskerPerson(String taskerPerson) {
        this.taskerPerson = taskerPerson;
    }
}
