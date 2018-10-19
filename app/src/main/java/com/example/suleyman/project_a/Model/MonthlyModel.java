package com.example.suleyman.project_a.Model;

/**
 * Created by Art Servis on 11/29/2017.
 */

public class MonthlyModel {

    private String month;
    private String previosYear;
    private String currentYear;
    private String difference;
    private  String imageUrl;

   /* public MonthlyModel(String month, long previosYear, long currentYear, long difference) {
        this.month = month;
        this.previosYear = previosYear;
        this.currentYear = currentYear;
        this.difference = difference;
    }*/

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPreviosYear() {
        return previosYear;
    }

    public void setPreviosYear(String previosYear) {
        this.previosYear = previosYear;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
