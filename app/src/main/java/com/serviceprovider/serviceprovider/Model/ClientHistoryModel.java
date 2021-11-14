package com.serviceprovider.serviceprovider.Model;

public class ClientHistoryModel {

    String titles;
    String daTe;
    String description;
    String budget;

    public ClientHistoryModel() {
    }

    public ClientHistoryModel(String titles, String daTe, String description, String budget) {
        this.titles = titles;
        this.daTe = daTe;
        this.description = description;
        this.budget = budget;
    }

    public String getTitles() {
        return titles;
    }

    public String getDaTe() {
        return daTe;
    }

    public String getDescription() {
        return description;
    }

    public String getBudget() {
        return budget;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public void setDaTe(String daTe) {
        this.daTe = daTe;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
