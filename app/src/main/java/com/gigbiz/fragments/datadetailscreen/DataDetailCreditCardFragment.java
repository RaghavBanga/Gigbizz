package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.databinding.FragmentDataDetailCreditCardBinding;
import com.gigbiz.models.ApprovedCredit;
import com.gigbiz.models.RejectedCredit;
import com.gigbiz.models.SubmittedCredit;

import java.util.List;


public class DataDetailCreditCardFragment extends Fragment {

    FragmentDataDetailCreditCardBinding binding;
    List<ApprovedCredit> approved;
    List<RejectedCredit> rejected;
    List<SubmittedCredit> submitted;
    String shopno;
    int position;


    public DataDetailCreditCardFragment newInstance(List<ApprovedCredit> list1, List<RejectedCredit> list2, List<SubmittedCredit> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailCreditCardFragment();
    }

    public DataDetailCreditCardFragment() {
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
        binding = FragmentDataDetailCreditCardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tittle.setText(shopno);

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().beginTransaction()
                            .remove(DataDetailCreditCardFragment.this).commit();
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