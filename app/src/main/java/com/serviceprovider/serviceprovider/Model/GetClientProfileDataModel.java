package com.serviceprovider.serviceprovider.Model;

public class GetClientProfileDataModel {
    private String nameClient;
    private String emailClient;
    private String phoneClient;
    private String whatsApp;
    private String job;
    private String description;
    private String budgetClient;
    private String experinceClient;
    private String genderClient;
    private String WorkingTime;
    private String onlineStatus;
    private String bookedStatus;

    public GetClientProfileDataModel() {

    }

    public GetClientProfileDataModel(String nameClient, String emailClient, String phoneClient, String whatsApp, String job,
                                     String description, String budgetClient, String experinceClient,
                                     String genderClient, String workingTime,String onlineStatus,String bookedStatus) {
        this.nameClient = nameClient;
        this.emailClient = emailClient;
        this.phoneClient = phoneClient;
        this.whatsApp = whatsApp;
        this.job = job;
        this.description = description;
        this.budgetClient = budgetClient;
        this.experinceClient = experinceClient;
        this.genderClient = genderClient;
        this.WorkingTime = workingTime;
        this.onlineStatus = onlineStatus;
        this.bookedStatus = bookedStatus;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public String getBookedStatus() {
        return bookedStatus;
    }

    public String getNameClient() {
        return nameClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public String getPhoneClient() {
        return phoneClient;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public String getJob() {
        return job;
    }

    public String getDescription() {
        return description;
    }

    public String getBudgetClient() {
        return budgetClient;
    }

    public String getExperinceClient() {
        return experinceClient;
    }

    public String getGenderClient() {
        return genderClient;
    }

    public String getWorkingTime() {
        return WorkingTime;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public void setPhoneClient(String phoneClient) {
        this.phoneClient = phoneClient;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBudgetClient(String budgetClient) {
        this.budgetClient = budgetClient;
    }

    public void setExperinceClient(String experinceClient) {
        this.experinceClient = experinceClient;
    }

    public void setGenderClient(String genderClient) {
        this.genderClient = genderClient;
    }

    public void setWorkingTime(String workingTime) {
        WorkingTime = workingTime;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setBookedStatus(String bookedStatus) {
        this.bookedStatus = bookedStatus;
    }
}

