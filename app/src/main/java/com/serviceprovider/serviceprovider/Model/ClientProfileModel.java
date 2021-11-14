package com.serviceprovider.serviceprovider.Model;

public class ClientProfileModel {
    private String id;
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
    private String isOnline;
    private String isBooked;

    public ClientProfileModel(String id,String nameClient, String emailClient, String phoneClient, String whatsApp, String job, String description, String budgetClient, String experinceClient, String genderClient,String WorkingTime,String isOnline,String isBooked) {
        this.nameClient = nameClient;
        this.emailClient = emailClient;
        this.phoneClient = phoneClient;
        this.whatsApp = whatsApp;
        this.job = job;
        this.description = description;
        this.budgetClient = budgetClient;
        this.experinceClient = experinceClient;
        this.genderClient = genderClient;
        this.WorkingTime = WorkingTime;
        this.isOnline = isOnline;
        this.isBooked = isBooked;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public String getIsBooked() {
        return isBooked;
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


}
