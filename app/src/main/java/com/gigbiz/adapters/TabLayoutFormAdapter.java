package com.gigbiz.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gigbiz.fragments.airtelform.otherinfoairtel.OtherInfoAirtelFragment;
import com.gigbiz.fragments.airtelform.photoairtel.PhotoAirtelFragment;
import com.gigbiz.fragments.airtelform.shopinfoairtel.ShopInfoAirtelFragment;
import com.gigbiz.fragments.amazonform.otherinfoamazon.OtherInfoAmazonFragment;
import com.gigbiz.fragments.amazonform.shopinfoamazon.ShopInfoAmazonFragment;
import com.gigbiz.fragments.creditcardcommonform.CreditCardCommonFragment;
import com.gigbiz.fragments.flipkartform.otherinfoflipkart.OtherInfoFlipkartFragment;
import com.gigbiz.fragments.flipkartform.photoflipkart.PhotoFlipkartFormFragment;
import com.gigbiz.fragments.flipkartform.shopinfoflipkart.ShopInfoFlipkartFragment;
import com.gigbiz.fragments.fydoform.forfydosignup.SignUpFydoFragment;
import com.gigbiz.fragments.fydoform.otherfydo.OtherInfoFydoFragment;
import com.gigbiz.fragments.fydoform.photofydo.PhotoFydoFragment;
import com.gigbiz.fragments.goodworkerform.forsignup.SignupDetailFragment;
import com.gigbiz.fragments.goodworkerform.goodworkotherinfo.GoodWorkOtherInfoFragment;
import com.gigbiz.fragments.goodworkerform.goodworkphoto.DocPhotoFragment;
import com.gigbiz.fragments.insertloancommonform.LoanCommonFormFragment;
import com.gigbiz.fragments.insurancecommonform.InsuranceCommonFormFragment;
import com.gigbiz.fragments.otherinfo.OtherInfoFragment;
import com.gigbiz.fragments.photo.PhotoFragment;
import com.gigbiz.fragments.pwform.OtherInfoPwFragment;
import com.gigbiz.fragments.pwform.PhotoPwFragment;
import com.gigbiz.fragments.pwform.ShopInfoPwFragment;
import com.gigbiz.fragments.shopinfo.ShopInfoFragment;

public class TabLayoutFormAdapter extends FragmentPagerAdapter {

    Context mContext;
    int totalTabs;
    public String formtype;
    public String type_list;
    public String gw_type;


    public TabLayoutFormAdapter(Context context, FragmentManager fm, int totalTabs, String formtype, String type_list) {
        super(fm);
        this.mContext = context;
        this.totalTabs = totalTabs;
        this.formtype = formtype;
        this.type_list = type_list;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (formtype.equals("goodworker")) {
                    SignupDetailFragment shopInfoFragment = new SignupDetailFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("amazon")) {
                    ShopInfoAmazonFragment shopInfoFragment = new ShopInfoAmazonFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("airtel")) {
                    ShopInfoAirtelFragment shopInfoFragment = new ShopInfoAirtelFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("fydo")) {
                    SignUpFydoFragment shopInfoFragment = new SignUpFydoFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("flipkart")) {
                    ShopInfoFlipkartFragment shopInfoFragment = new ShopInfoFlipkartFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("pw")) {
                    ShopInfoPwFragment shopInfoFragment = new ShopInfoPwFragment();
                    return shopInfoFragment;
                }else if (formtype.contains("credit")) {
                    CreditCardCommonFragment shopInfoFragment = new CreditCardCommonFragment();
                    return shopInfoFragment;
                } else if (formtype.contains("loans")) {
                    LoanCommonFormFragment shopInfoFragment = new LoanCommonFormFragment();
                    return shopInfoFragment;
                } else if (formtype.contains("insurance")) {
                    InsuranceCommonFormFragment shopInfoFragment = new InsuranceCommonFormFragment();
                    return shopInfoFragment;
                } else {
                    ShopInfoFragment shopInfoFragment = new ShopInfoFragment();
                    return shopInfoFragment;
                }
            case 1:
                if (formtype.equals("goodworker")) {
                    DocPhotoFragment photoFragment = new DocPhotoFragment();
                    return photoFragment;
                } else if (formtype.equals("amazon")) {
                    DocPhotoFragment shopInfoFragment = new DocPhotoFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("airtel")) {
                    PhotoAirtelFragment shopInfoFragment = new PhotoAirtelFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("fydo")) {
                    PhotoFydoFragment shopInfoFragment = new PhotoFydoFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("flipkart")) {
                    PhotoFlipkartFormFragment shopInfoFragment = new PhotoFlipkartFormFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("pw")) {
                    PhotoPwFragment shopInfoFragment = new PhotoPwFragment();
                    return shopInfoFragment;
                }else if (formtype.contains("credit")) {
                    CreditCardCommonFragment shopInfoFragment = new CreditCardCommonFragment();
                    return shopInfoFragment;
                } else if (formtype.contains("loans")) {
                    LoanCommonFormFragment shopInfoFragment = new LoanCommonFormFragment();
                    return shopInfoFragment;
                } else if (formtype.contains("insurance")) {
                    InsuranceCommonFormFragment shopInfoFragment = new InsuranceCommonFormFragment();
                    return shopInfoFragment;
                }  else {
                    PhotoFragment shopInfoFragment = new PhotoFragment();
                    return shopInfoFragment;
                }
            case 2:
                if (formtype.equals("goodworker")) {
                    GoodWorkOtherInfoFragment photoFragment = new GoodWorkOtherInfoFragment();
                    return photoFragment;
                } else if (formtype.equals("amazon")) {
                    OtherInfoAmazonFragment shopInfoFragment = new OtherInfoAmazonFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("airtel")) {
                    OtherInfoAirtelFragment shopInfoFragment = new OtherInfoAirtelFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("fydo")) {
                    OtherInfoFydoFragment shopInfoFragment = new OtherInfoFydoFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("pw")) {
                    OtherInfoPwFragment shopInfoFragment = new OtherInfoPwFragment();
                    return shopInfoFragment;
                } else if (formtype.equals("flipkart")) {
                    OtherInfoFlipkartFragment shopInfoFragment = new OtherInfoFlipkartFragment();
                    return shopInfoFragment;
                }else if (formtype.contains("credit")) {
                    CreditCardCommonFragment shopInfoFragment = new CreditCardCommonFragment();
                    return shopInfoFragment;
                } else if (formtype.contains("loans")) {
                    LoanCommonFormFragment shopInfoFragment = new LoanCommonFormFragment();
                    return shopInfoFragment;
                } else if (formtype.contains("insurance")) {
                    InsuranceCommonFormFragment shopInfoFragment = new InsuranceCommonFormFragment();
                    return shopInfoFragment;
                }  else {
                    OtherInfoFragment shopInfoFragment = new OtherInfoFragment();
                    return shopInfoFragment;
                }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}