package com.calliemao.gasmeter.ui;

/**
 * Created by alex on 11/7/15.
 */
public class StatItem {

    String photoID;
    String description;
    double amount;

    StatItem(String photoID, String description, double amount){
        this.photoID = photoID;
        this.description = description;
        this.amount = amount;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
