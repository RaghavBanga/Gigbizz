package com.gigbiz.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gigbiz.fragments.details.DetailTabFragment;
import com.gigbiz.fragments.eligibility.EligibilityTabFragment;
import com.gigbiz.fragments.faqs.FaqsFragment;
import com.gigbiz.fragments.stepfollow.StepToFollowTabFragment;
import com.gigbiz.fragments.termsandconditions.TermsAndConditionFragment;

public class DescriptionSubTabAdapter  extends FragmentPagerAdapter {

    Context mContext;
    int totalTabs;


    public DescriptionSubTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailTabFragment detailTabFragment = new DetailTabFragment();
                return detailTabFragment;
            case 1:
                EligibilityTabFragment eligibilityTabFragment = new EligibilityTabFragment();
                return eligibilityTabFragment;
            case 2:
                StepToFollowTabFragment stepToFollowTabFragment = new StepToFollowTabFragment();
                return stepToFollowTabFragment;
            case 3:
                TermsAndConditionFragment termsAndConditionFragment = new TermsAndConditionFragment();
                return termsAndConditionFragment;
            case 4:
                FaqsFragment faqsFragment = new FaqsFragment();
                return faqsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}