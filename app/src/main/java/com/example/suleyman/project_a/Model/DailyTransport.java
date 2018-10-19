package com.example.suleyman.project_a.Model;

import android.widget.ImageView;

/**
 * Created by Art Servis on 11/30/2017.
 */

public class DailyTransport {

    private String cargoName;
    private  int   transport2016;
    private int   Transport2017;
    private int difference;
    private ImageView imageView;

    public DailyTransport(String cargoName, int transport2016, int transport2017, int difference) {
        this.cargoName = cargoName;
        this.transport2016 = transport2016;
        Transport2017 = transport2017;
        this.difference = difference;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public int getTransport2016() {
        return transport2016;
    }

    public void setTransport2016(int transport2016) {
        this.transport2016 = transport2016;
    }

    public int getTransport2017() {
        return Transport2017;
    }

    public void setransport2017(int Transport2017) {
        this.Transport2017 = Transport2017;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
