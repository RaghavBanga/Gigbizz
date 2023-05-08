package com.gigbiz.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gigbiz.fragments.airtelreportlist.ApprovedAirtelFragment;
import com.gigbiz.fragments.airtelreportlist.RejectedAirtelFragment;
import com.gigbiz.fragments.airtelreportlist.SubmittedAirtelFragment;
import com.gigbiz.fragments.allotherreportlist.ApprovedOtherFragment;
import com.gigbiz.fragments.allotherreportlist.RejectedOtherFragment;
import com.gigbiz.fragments.allotherreportlist.SubmittedOtherFragment;
import com.gigbiz.fragments.amazonreportlist.ApprovedAmazonFragment;
import com.gigbiz.fragments.amazonreportlist.RejectedAmazonFragment;
import com.gigbiz.fragments.amazonreportlist.SubmittedAmazonFragment;
import com.gigbiz.fragments.approved.ApprovedFragment;
import com.gigbiz.fragments.flipkartreportlist.ApprovedFlipkartFragment;
import com.gigbiz.fragments.flipkartreportlist.RejectedFlipkartFragment;
import com.gigbiz.fragments.flipkartreportlist.SubmittedFlipkartFragment;
import com.gigbiz.fragments.fydoreportlist.ApprovedFydoFragment;
import com.gigbiz.fragments.fydoreportlist.RejectedFydoFragment;
import com.gigbiz.fragments.fydoreportlist.SubmittedFydoFragment;
import com.gigbiz.fragments.pwreportlist.ApprovedPwFragment;
import com.gigbiz.fragments.pwreportlist.RejectedPwFragment;
import com.gigbiz.fragments.pwreportlist.SubmittedPwFragment;
import com.gigbiz.fragments.rejected.RejectedFragment;
import com.gigbiz.fragments.reportlistcreditcardcommon.ApprovedCreditCardCommonFragment;
import com.gigbiz.fragments.reportlistcreditcardcommon.RejectedCreditCardCommonFragment;
import com.gigbiz.fragments.reportlistcreditcardcommon.SubmittedCreditCardCommonFragment;
import com.gigbiz.fragments.reportlistinsurancecommon.ApprovedInsuranceCommonFragment;
import com.gigbiz.fragments.reportlistinsurancecommon.RejectedInsuranceCommonFragment;
import com.gigbiz.fragments.reportlistinsurancecommon.SubmittedInsuranceCommonFragment;
import com.gigbiz.fragments.reportlistloancommon.ApprovedLoanCommonFragment;
import com.gigbiz.fragments.reportlistloancommon.RejectedLoanCommonFragment;
import com.gigbiz.fragments.reportlistloancommon.SubmittedLoanCommonFragment;
import com.gigbiz.fragments.submitted.SubmittedFragment;

public class TabLayoutReportListAdapter extends FragmentPagerAdapter {

    Context mContext;
    int totalTabs;
    public String formtype;


    public TabLayoutReportListAdapter(Context context, FragmentManager fm, int totalTabs, String form_type) {
        super(fm);
        this.mContext = context;
        this.totalTabs = totalTabs;
        this.formtype = form_type;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (formtype.equals("goodworker")){
                    SubmittedFragment shopInfoFragment = new SubmittedFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("amazon")){
                    SubmittedAmazonFragment shopInfoFragment = new SubmittedAmazonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("airtel")){
                    SubmittedAirtelFragment shopInfoFragment = new SubmittedAirtelFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("fydo")){
                    SubmittedFydoFragment shopInfoFragment = new SubmittedFydoFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("flipkart")){
                    SubmittedFlipkartFragment shopInfoFragment = new SubmittedFlipkartFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("pw")){
                    SubmittedPwFragment shopInfoFragment = new SubmittedPwFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("loans")){
                    SubmittedLoanCommonFragment shopInfoFragment = new SubmittedLoanCommonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("insurance")){
                    SubmittedInsuranceCommonFragment shopInfoFragment = new SubmittedInsuranceCommonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("credit")){
                    SubmittedCreditCardCommonFragment shopInfoFragment = new SubmittedCreditCardCommonFragment();
                    return shopInfoFragment;
                }else{
                    SubmittedOtherFragment shopInfoFragment = new SubmittedOtherFragment();
                    return shopInfoFragment;
                }
            case 1:
                if (formtype.equals("goodworker")){
                    ApprovedFragment photoFragment = new ApprovedFragment();
                    return photoFragment;
                }else if (formtype.equals("amazon")){
                    ApprovedAmazonFragment shopInfoFragment = new ApprovedAmazonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("airtel")){
                    ApprovedAirtelFragment shopInfoFragment = new ApprovedAirtelFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("fydo")){
                    ApprovedFydoFragment shopInfoFragment = new ApprovedFydoFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("flipkart")){
                    ApprovedFlipkartFragment shopInfoFragment = new ApprovedFlipkartFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("pw")){
                    ApprovedPwFragment shopInfoFragment = new ApprovedPwFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("loans")){
                    ApprovedLoanCommonFragment shopInfoFragment = new ApprovedLoanCommonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("insurance")){
                    ApprovedInsuranceCommonFragment shopInfoFragment = new ApprovedInsuranceCommonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("credit")){
                    ApprovedCreditCardCommonFragment shopInfoFragment = new ApprovedCreditCardCommonFragment();
                    return shopInfoFragment;
                }else{
                    ApprovedOtherFragment shopInfoFragment = new ApprovedOtherFragment();
                    return shopInfoFragment;
                }

            case 2:
                if (formtype.equals("goodworker")){
                    RejectedFragment photoFragment = new RejectedFragment();
                    return photoFragment;
                }else if (formtype.equals("amazon")){
                    RejectedAmazonFragment shopInfoFragment = new RejectedAmazonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("airtel")){
                    RejectedAirtelFragment shopInfoFragment = new RejectedAirtelFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("fydo")){
                    RejectedFydoFragment shopInfoFragment = new RejectedFydoFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("flipkart")){
                    RejectedFlipkartFragment shopInfoFragment = new RejectedFlipkartFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("pw")){
                    RejectedPwFragment shopInfoFragment = new RejectedPwFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("loans")){
                    RejectedLoanCommonFragment shopInfoFragment = new RejectedLoanCommonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("insurance")){
                    RejectedInsuranceCommonFragment shopInfoFragment = new RejectedInsuranceCommonFragment();
                    return shopInfoFragment;
                }else if (formtype.equals("credit")){
                    RejectedCreditCardCommonFragment shopInfoFragment = new RejectedCreditCardCommonFragment();
                    return shopInfoFragment;
                }else {
                    RejectedOtherFragment shopInfoFragment = new RejectedOtherFragment();
                    return shopInfoFragment;
                }
            default:
                return null;
        }
//        switch (position) {
//            case 0:
//                SubmittedFragment submittedFragment = new SubmittedFragment();
//                return submittedFragment;
//            case 1:
//                ApprovedFragment approvedFragment = new ApprovedFragment();
//                return approvedFragment;
//            case 2:
//                RejectedFragment rejectedFragment = new RejectedFragment();
//                return rejectedFragment;
//            default:
//                return null;
//        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}