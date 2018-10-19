package com.example.suleyman.project_a.Model;

/**
 * Created by Art Servis on 1/28/2018.
 */

public class MonthlyPopupModel {
    private String qnq;
    private String totalImgUrl;
    private String firstYear;
    private String secondYear;
    private String adyFirstYear;
    private String adySecondYear;
    private String adyDiff;
    private String adyImgUrl;
    private String ttFirstYear;
    private String ttSecondYear;
    private String ttDiff;
    private String ttImgUrl;
    private String cemFirstYear;
    private String cemSecondYear;
    private String cemDiff;
    private String cemImgUrl;

    public MonthlyPopupModel(String qnq, String firstYear, String secondYear,
                             String adyFirstYear, String adySecondYear,  String adyDiff, String adyImgUrl,
                             String ttFirstYear,
                             String ttSecondYear, String ttDiff, String ttImgUrl,
                             String cemFirstYear, String cemSecondYear, String cemDiff, String cemImgUrl, String totalImgUrl) {
        this.qnq = qnq;
        this.firstYear = firstYear;
        this.secondYear = secondYear;
        this.adyFirstYear = adyFirstYear;
        this.adySecondYear = adySecondYear;
        this.ttFirstYear = ttFirstYear;
        this.ttSecondYear = ttSecondYear;
        this.cemFirstYear = cemFirstYear;
        this.cemSecondYear = cemSecondYear;
        this.adyDiff = adyDiff;
        this.ttDiff = ttDiff;
        this.cemDiff = cemDiff;
        this.adyImgUrl = adyImgUrl;
        this.ttImgUrl = ttImgUrl;
        this.cemImgUrl = cemImgUrl;
        this.totalImgUrl  = totalImgUrl;

    }

    public String getQnq() {
        return qnq;
    }

    public void setQnq(String qnq) {
        this.qnq = qnq;
    }

    public String getTotalImgUrl() {
        return totalImgUrl;
    }

    public void setTotalImgUrl(String totalImgUrl) {
        this.totalImgUrl = totalImgUrl;
    }

    public String getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(String firstYear) {
        this.firstYear = firstYear;
    }

    public String getSecondYear() {
        return secondYear;
    }

    public void setSecondYear(String secondYear) {
        this.secondYear = secondYear;
    }

    public String getAdyFirstYear() {
        return adyFirstYear;
    }

    public void setAdyFirstYear(String adyFirstYear) {
        this.adyFirstYear = adyFirstYear;
    }

    public String getAdySecondYear() {
        return adySecondYear;
    }

    public void setAdySecondYear(String adySecondYear) {
        this.adySecondYear = adySecondYear;
    }

    public String getAdyImgUrl() {
        return adyImgUrl;
    }

    public void setAdyImgUrl(String adyImgUrl) {
        this.adyImgUrl = adyImgUrl;
    }

    public String getTtFirstYear() {
        return ttFirstYear;
    }

    public void setTtFirstYear(String ttFirstYear) {
        this.ttFirstYear = ttFirstYear;
    }

    public String getTtSecondYear() {
        return ttSecondYear;
    }

    public void setTtSecondYear(String ttSecondYear) {
        this.ttSecondYear = ttSecondYear;
    }

    public String getTtImgUrl() {
        return ttImgUrl;
    }

    public void setTtImgUrl(String ttImgUrl) {
        this.ttImgUrl = ttImgUrl;
    }

    public String getCemFirstYear() {
        return cemFirstYear;
    }

    public void setCemFirstYear(String cemFirstYear) {
        this.cemFirstYear = cemFirstYear;
    }

    public String getCemSecondYear() {
        return cemSecondYear;
    }

    public void setCemSecondYear(String cemSecondYear) {
        this.cemSecondYear = cemSecondYear;
    }

    public String getCemImgUrl() {
        return cemImgUrl;
    }

    public void setCemImgUrl(String cemImgUrl) {
        this.cemImgUrl = cemImgUrl;
    }

    public String getAdyDiff() {
        return adyDiff;
    }

    public void setAdyDiff(String adyDiff) {
        this.adyDiff = adyDiff;
    }

    public String getTtDiff() {
        return ttDiff;
    }

    public void setTtDiff(String ttDiff) {
        this.ttDiff = ttDiff;
    }

    public String getCemDiff() {
        return cemDiff;
    }

    public void setCemDiff(String cemDiff) {
        this.cemDiff = cemDiff;
    }
}
