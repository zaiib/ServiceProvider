package com.serviceprovider.serviceprovider.Model;


public class
SendTaskToFirebaseModel {

    String taskId;
    String tasknames;
    String DespTask;
    String amounts;
    String budgets;
    String addressUser;
    String userPhone;
    String dates;
    String locations;

//    public SendTaskToFirebaseModel() {
//    }

    public SendTaskToFirebaseModel(String taskId, String tasknames, String despTask, String amounts, String budgets, String addressUser, String userPhone, String dates, String locations) {
        this.taskId = taskId;
        this.tasknames = tasknames;
        DespTask = despTask;
        this.amounts = amounts;
        this.budgets = budgets;
        this.addressUser = addressUser;
        this.userPhone = userPhone;
        this.dates = dates;
        this.locations = locations;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTasknames() {
        return tasknames;
    }

    public String getDespTask() {
        return DespTask;
    }

    public String getAmounts() {
        return amounts;
    }

    public String getBudgets() {
        return budgets;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getDates() {
        return dates;
    }

    public String getLocations() {
        return locations;
    }
}
