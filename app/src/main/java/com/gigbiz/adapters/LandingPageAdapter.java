package com.gigbiz.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gigbiz.fragments.descriptiontab.DescriptionTabFragment;
import com.gigbiz.fragments.earningtab.EarningTabFragment;
import com.gigbiz.fragments.mediacontenttab.MediaContentTabFragment;
import com.gigbiz.fragments.trainingtab.TrainingTabFragment;

public class LandingPageAdapter extends FragmentPagerAdapter {

    Context mContext;
    int totalTabs;


    public LandingPageAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                    EarningTabFragment earningTabFragment = new EarningTabFragment();
                    return earningTabFragment;
            case 1:
                    DescriptionTabFragment descriptionTabFragment = new DescriptionTabFragment();
                    return descriptionTabFragment;

            case 2:
                    TrainingTabFragment trainingTabFragment = new TrainingTabFragment();
                    return trainingTabFragment;
            case 3:
                MediaContentTabFragment mediaContentTabFragment = new MediaContentTabFragment();
                return mediaContentTabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}