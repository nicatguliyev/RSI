package com.example.suleyman.project_a.Model;

/**
 * Created by Art Servis on 11/26/2017.
 */

public class Expenses {

    private String expenseName;
    private String buyyingPrice;
    private String sellingPrice;
    private String buyingMpsPrice;
    private String sellingMpsPrice;
    private String buyingSpsPrice;
    private String sellingSpsPrice;

    public Expenses(String expenseName, String buyyingPrice, String sellingPrice) {
        this.expenseName = expenseName;
        this.buyyingPrice = buyyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public Expenses( String expenseName , String buyingMpsPrice, String buyingSpsPrice, String sellingMpsPrice, String sellingSpsPrice)
    {
        this.buyingMpsPrice = buyingMpsPrice;
        this.buyingSpsPrice = buyingSpsPrice;
        this.sellingMpsPrice = sellingMpsPrice;
        this.sellingSpsPrice =sellingSpsPrice;
        this.expenseName = expenseName;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getBuyyingPrice() {
        return buyyingPrice;
    }

    public void setBuyyingPrice(String buyyingPrice) {
        this.buyyingPrice = buyyingPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getBuyingMpsPrice() {
        return buyingMpsPrice;
    }

    public void setBuyingMpsPrice(String buyingMpsPrice) {
        this.buyingMpsPrice = buyingMpsPrice;
    }

    public String getSellingMpsPrice() {
        return sellingMpsPrice;
    }

    public void setSellingMpsPrice(String sellingMpsPrice) {
        this.sellingMpsPrice = sellingMpsPrice;
    }

    public String getBuyingSpsPrice() {
        return buyingSpsPrice;
    }

    public void setBuyingSpsPrice(String buyingSpsPrice) {
        this.buyingSpsPrice = buyingSpsPrice;
    }

    public String getSellingSpsPrice() {
        return sellingSpsPrice;
    }

    public void setSellingSpsPrice(String sellingSpsPrice) {
        this.sellingSpsPrice = sellingSpsPrice;
    }
}
