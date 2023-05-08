package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.databinding.FragmentDataDetailPWBinding;
import com.gigbiz.models.ApprovedPw;
import com.gigbiz.models.RejectedPw;
import com.gigbiz.models.SubmittedPw;

import java.util.List;


public class DataDetailPWFragment extends Fragment {

    FragmentDataDetailPWBinding binding;
    List<ApprovedPw> approved;
    List<RejectedPw> rejected;
    List<SubmittedPw> submitted;
    String shopno;
    int position;

    public DataDetailPWFragment() {
        // Required empty public constructor
    }

    public DataDetailPWFragment newInstance(List<ApprovedPw> list1, List<RejectedPw> list2, List<SubmittedPw> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailPWFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDataDetailPWBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tittle.setText(shopno);

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    DataDetailScreenFragment dataDetailScreenFragment = new DataDetailScreenFragment();
//                    FragmentManager manager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction trans = manager.beginTransaction();
//                    trans.remove(dataDetailScreenFragment);
//                    trans.commit();
//                    manager.popBackStack();

                    getFragmentManager().beginTransaction()
                            .remove(DataDetailPWFragment.this).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        setData();

        return root;
    }

    private void setData() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    if (submitted!=null){
                        try {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.email.setText(submitted.get(position).getEmail());
                                    binding.name.setText(submitted.get(position).getName());
                                    binding.age.setText(submitted.get(position).getAge());
                                    binding.gender.setText(submitted.get(position).getGender());
                                    binding.bookSellerEmail.setText(submitted.get(position).getSellerEmailId());
                                    binding.phoneNumber.setText(submitted.get(position).getAltPhoneNo());
                                    binding.alterPhoneNumber.setText(submitted.get(position).getAltPhoneNo());
                                    binding.educationalQualification.setText(submitted.get(position).getEducation());
                                    binding.state.setText(submitted.get(position).getState());
                                    binding.City.setText(submitted.get(position).getCity());
                                    binding.bookStoreName.setText(submitted.get(position).getBookStoreName());
                                    binding.storeAddress.setText(submitted.get(position).getStoreAddress());
                                    binding.pinCode.setText(submitted.get(position).getPincode());
                                    binding.shopLocationLink.setText(submitted.get(position).getShopLocationLink());
                                    binding.schoolNo.setText(submitted.get(position).getSellerSupplyingBookSchool());
                                    binding.schoolName.setText(submitted.get(position).getSchoolName());
                                    binding.noOfStaff.setText(submitted.get(position).getNoOfStaff());
                                    binding.monthlyIncome.setText(submitted.get(position).getMonthlyIncome());
                                    binding.accountNo.setText(submitted.get(position).getAccountNo());
                                    binding.accountHolderName.setText(submitted.get(position).getAccountName());
                                    binding.bankName.setText(submitted.get(position).getBankName());
                                    binding.ifscNo.setText(submitted.get(position).getIfscCode());
                                    binding.panNo.setText(submitted.get(position).getPanNo());
                                    binding.gstNo.setText(submitted.get(position).getGstNo());
                                    binding.employeeEmail.setText(submitted.get(position).getPwEmpEmail());
                                    binding.employeeId.setText(submitted.get(position).getPwEmpId());
                                    binding.employeeName.setText(submitted.get(position).getPwEmpName());
                                    binding.reason.setText(submitted.get(position).getReason());
                                    binding.pwcoupon.setText(submitted.get(position).getDiscount_coupon_code());

                                    Glide.with(getContext())
                                            .load(submitted.get(position).getShopPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.shopPhotoImg);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getShopFrontPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.storeFrontImg);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getPassbookChequePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.passbookImg);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getPanCard())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.panCardImg);


                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (approved!=null){

                        try {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.email.setText(approved.get(position).getEmail());
                                    binding.name.setText(approved.get(position).getName());
                                    binding.age.setText(approved.get(position).getAge());
                                    binding.gender.setText(approved.get(position).getGender());
                                    binding.bookSellerEmail.setText(approved.get(position).getSellerEmailId());
                                    binding.phoneNumber.setText(approved.get(position).getAltPhoneNo());
                                    binding.alterPhoneNumber.setText(approved.get(position).getAltPhoneNo());
                                    binding.educationalQualification.setText(approved.get(position).getEducation());
                                    binding.state.setText(approved.get(position).getState());
                                    binding.City.setText(approved.get(position).getCity());
                                    binding.bookStoreName.setText(approved.get(position).getBookStoreName());
                                    binding.storeAddress.setText(approved.get(position).getStoreAddress());
                                    binding.pinCode.setText(approved.get(position).getPincode());
                                    binding.shopLocationLink.setText(approved.get(position).getShopLocationLink());
                                    binding.schoolNo.setText(approved.get(position).getSellerSupplyingBookSchool());
                                    binding.schoolName.setText(approved.get(position).getSchoolName());
                                    binding.noOfStaff.setText(approved.get(position).getNoOfStaff());
                                    binding.monthlyIncome.setText(approved.get(position).getMonthlyIncome());
                                    binding.accountNo.setText(approved.get(position).getAccountNo());
                                    binding.accountHolderName.setText(approved.get(position).getAccountName());
                                    binding.bankName.setText(approved.get(position).getBankName());
                                    binding.ifscNo.setText(approved.get(position).getIfscCode());
                                    binding.panNo.setText(approved.get(position).getPanNo());
                                    binding.gstNo.setText(approved.get(position).getGstNo());
                                    binding.employeeEmail.setText(approved.get(position).getPwEmpEmail());
                                    binding.employeeId.setText(approved.get(position).getPwEmpId());
                                    binding.employeeName.setText(approved.get(position).getPwEmpName());
                                    binding.reason.setText(approved.get(position).getReason());
                                    binding.pwcoupon.setText(approved.get(position).getDiscount_coupon_code());


                                    Glide.with(getContext())
                                            .load(approved.get(position).getShopPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.shopPhotoImg);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getShopFrontPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.storeFrontImg);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getPassbookChequePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.passbookImg);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getPanCard())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.panCardImg);


                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else if (rejected!=null){
                        try {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.email.setText(rejected.get(position).getEmail());
                                    binding.name.setText(rejected.get(position).getName());
                                    binding.age.setText(rejected.get(position).getAge());
                                    binding.gender.setText(rejected.get(position).getGender());
                                    binding.bookSellerEmail.setText(rejected.get(position).getSellerEmailId());
                                    binding.phoneNumber.setText(rejected.get(position).getAltPhoneNo());
                                    binding.alterPhoneNumber.setText(rejected.get(position).getAltPhoneNo());
                                    binding.educationalQualification.setText(rejected.get(position).getEducation());
                                    binding.state.setText(rejected.get(position).getState());
                                    binding.City.setText(rejected.get(position).getCity());
                                    binding.bookStoreName.setText(rejected.get(position).getBookStoreName());
                                    binding.storeAddress.setText(rejected.get(position).getStoreAddress());
                                    binding.pinCode.setText(rejected.get(position).getPincode());
                                    binding.shopLocationLink.setText(rejected.get(position).getShopLocationLink());
                                    binding.schoolNo.setText(rejected.get(position).getSellerSupplyingBookSchool());
                                    binding.schoolName.setText(rejected.get(position).getSchoolName());
                                    binding.noOfStaff.setText(rejected.get(position).getNoOfStaff());
                                    binding.monthlyIncome.setText(rejected.get(position).getMonthlyIncome());
                                    binding.accountNo.setText(rejected.get(position).getAccountNo());
                                    binding.accountHolderName.setText(rejected.get(position).getAccountName());
                                    binding.bankName.setText(rejected.get(position).getBankName());
                                    binding.ifscNo.setText(rejected.get(position).getIfscCode());
                                    binding.panNo.setText(rejected.get(position).getPanNo());
                                    binding.gstNo.setText(rejected.get(position).getGstNo());
                                    binding.employeeEmail.setText(rejected.get(position).getPwEmpEmail());
                                    binding.employeeId.setText(rejected.get(position).getPwEmpId());
                                    binding.employeeName.setText(rejected.get(position).getPwEmpName());
                                    binding.reason.setText(rejected.get(position).getReason());
                                    binding.pwcoupon.setText(rejected.get(position).getDiscount_coupon_code());


                                    Glide.with(getContext())
                                            .load(rejected.get(position).getShopPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.shopPhotoImg);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getShopFrontPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.storeFrontImg);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getPassbookChequePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.passbookImg);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getPanCard())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.panCardImg);
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}