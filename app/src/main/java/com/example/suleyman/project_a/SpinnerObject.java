package com.example.suleyman.project_a;

public class SpinnerObject {

    private  int databaseId;
    private  int pos;
    private String databaseValue;

    public SpinnerObject ( int databaseId , int pos, String databaseValue ) {
        this.databaseId = databaseId;
        this.pos=pos;
        this.databaseValue = databaseValue;
    }

    public int getId () {
        return databaseId;
    }
    public int getPos () {
        return pos;
    }
    public String getValue () {
        return databaseValue;
    }

    @Override
    public String toString () {
        return databaseValue;
    }

}


