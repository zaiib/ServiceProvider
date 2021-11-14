package com.serviceprovider.serviceprovider.Model;

public class TaskViewToClientModel {

    String userAddress;
    String userLocation;
    String taskerAmount;
    String taskBudget;
    String taskDate;
    String taskName;
    String taskDesp;
    String userPhoneNumber;
    String id;

    public TaskViewToClientModel() {
    }

    public TaskViewToClientModel(String userAddress, String userLocation, String taskerAmount, String taskBudget, String taskDate, String taskName, String taskDesp, String userPhoneNumber, String id) {
        this.userAddress = userAddress;
        this.userLocation = userLocation;
        this.taskerAmount = taskerAmount;
        this.taskBudget = taskBudget;
        this.taskDate = taskDate;
        this.taskName = taskName;
        this.taskDesp = taskDesp;
        this.userPhoneNumber = userPhoneNumber;
        this.id=id;
    }


    public String getUserAddress() {
        return userAddress;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getTaskerAmount() {
        return taskerAmount;
    }

    public String getTaskBudget() {
        return taskBudget;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDesp() {
        return taskDesp;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public void setTaskerAmount(String taskerAmount) {
        this.taskerAmount = taskerAmount;
    }

    public void setTaskBudget(String taskBudget) {
        this.taskBudget = taskBudget;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDesp(String taskDesp) {
        this.taskDesp = taskDesp;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }
}
