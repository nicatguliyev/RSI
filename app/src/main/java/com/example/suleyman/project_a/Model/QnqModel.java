package com.example.suleyman.project_a.Model;

/**
 * Created by Art Servis on 6/25/2018.
 */

public class QnqModel {

    String qnqCode;
    String qnqName;

    public String getQnqCode() {
        return qnqCode;
    }

    public void setQnqCode(String qnqCode) {
        this.qnqCode = qnqCode;
    }

    public String getQnqName() {
        return qnqName;
    }

    public void setQnqName(String qnqName) {
        this.qnqName = qnqName;
    }

    public QnqModel(String qnqCode, String qnqName) {
        this.qnqCode = qnqCode;
        this.qnqName = qnqName;

    }
}
