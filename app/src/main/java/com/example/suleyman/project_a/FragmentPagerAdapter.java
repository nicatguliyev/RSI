package com.example.suleyman.project_a;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Art Servis on 2/6/2018.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public FragmentPagerAdapter(FragmentManager fm, int NumofTabs) {
        super(fm);
        this.mNumOfTabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllFragment tab1 = new AllFragment();
                return tab1;
            case 1:
                IdxalFragment tab2 = new IdxalFragment();
                return tab2;
            case 2:
                IxracFragment tab3 = new IxracFragment();
                return tab3;
            case 3:
                TranzitFragment tab4 = new TranzitFragment();
                return tab4;
            case 4:
                DaxiliFragment tab5 = new DaxiliFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
