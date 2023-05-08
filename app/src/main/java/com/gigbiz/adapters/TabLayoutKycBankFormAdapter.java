package com.gigbiz.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gigbiz.fragments.bankdetails.BankDetailFragment;
import com.gigbiz.fragments.uploadkyc.UploadKycFragment;

public class TabLayoutKycBankFormAdapter extends FragmentPagerAdapter {

    Context mContext;
    int totalTabs;


    public TabLayoutKycBankFormAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mContext = context;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                    UploadKycFragment uploadKycFragment = new UploadKycFragment();
                    return  uploadKycFragment;

            case 1:
                    BankDetailFragment bankDetailFragment = new BankDetailFragment();

                    return bankDetailFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}