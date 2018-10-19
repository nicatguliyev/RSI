package com.example.suleyman.project_a;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import static com.example.suleyman.project_a.TabFragment.int_items;
public class MyAdapter  extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new pblgrp();
            case 1:
                return new pblgrp();
            case 2:
                return new pblgrp();
            case 3:
                return new pblgrp();
            case 4:
                return new pblgrp();
            case 5:
                return new pblgrp();
            case 6:
                return new pblgrp();
            case 7:
                return new pblgrp();
            case 8:
                return new pblgrp();
            case 9:
                return new pblgrp();
            case 10:
                return new pblgrp();
            case 11:
                return new pblgrp();
            case 12:
                return new pblgrp();


        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return LLanguage.tab[0];
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "March";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "Avq";
            case 9:
                return "Seb";
            case 10:
                return "Oct";
            case 11:
                return "Noy";
            case 12:
                return "Dec";


        }

        return null;
    }

}
