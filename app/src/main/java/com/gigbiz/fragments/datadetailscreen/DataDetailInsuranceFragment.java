package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.databinding.FragmentDataDetailInsuranceBinding;
import com.gigbiz.models.ApprovedInsurance;
import com.gigbiz.models.RejectedInsurance;
import com.gigbiz.models.SubmittedInsurance;

import java.util.List;


public class DataDetailInsuranceFragment extends Fragment {

    FragmentDataDetailInsuranceBinding binding;
    List<ApprovedInsurance> approved;
    List<RejectedInsurance> rejected;
    List<SubmittedInsurance> submitted;
    String shopno;
    int position;


    public DataDetailInsuranceFragment newInstance(List<ApprovedInsurance> list1, List<RejectedInsurance> list2, List<SubmittedInsurance> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailInsuranceFragment();
    }

    public DataDetailInsuranceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataDetailInsuranceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tittle.setText(shopno);

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().beginTransaction()
                            .remove(DataDetailInsuranceFragment.this).commit();
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
                                    binding.name.setText(submitted.get(position).getName());
                                    binding.email.setText(submitted.get(position).getEmail());
                                    binding.mobNumber.setText(submitted.get(position).getMobile());
                                    binding.interesttype.setText(submitted.get(position).getInsuranceType());
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getAadharFront())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.aadhaarFrontImg);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getAadharBack())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.aadhaarBackImg);

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (approved!=null){

                        try {

                            binding.name.setText(approved.get(position).getName());
                            binding.email.setText(approved.get(position).getEmail());
                            binding.mobNumber.setText(approved.get(position).getMobile());
                            binding.interesttype.setText(approved.get(position).getInsuranceType());

                            Glide.with(getContext())
                                    .load(approved.get(position).getAadharFront())
                                    .centerCrop()
                                    .placeholder(R.drawable.logogigbiz)
                                    .into(binding.aadhaarFrontImg);
                            Glide.with(getContext())
                                    .load(approved.get(position).getAadharBack())
                                    .centerCrop()
                                    .placeholder(R.drawable.logogigbiz)
                                    .into(binding.aadhaarBackImg);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else if (rejected!=null){
                        try {
                            binding.name.setText(rejected.get(position).getName());
                            binding.email.setText(rejected.get(position).getEmail());
                            binding.mobNumber.setText(rejected.get(position).getMobile());
                            binding.interesttype.setText(rejected.get(position).getInsuranceType());

                            Glide.with(getContext())
                                    .load(rejected.get(position).getAadharFront())
                                    .centerCrop()
                                    .placeholder(R.drawable.logogigbiz)
                                    .into(binding.aadhaarFrontImg);
                            Glide.with(getContext())
                                    .load(rejected.get(position).getAadharBack())
                                    .centerCrop()
                                    .placeholder(R.drawable.logogigbiz)
                                    .into(binding.aadhaarBackImg);

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