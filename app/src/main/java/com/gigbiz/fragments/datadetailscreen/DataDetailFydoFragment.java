package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.databinding.FragmentDataDetailFydoBinding;
import com.gigbiz.models.ApprovedFydo;
import com.gigbiz.models.RejectedFydo;
import com.gigbiz.models.SubmittedFydo;

import java.util.List;


public class DataDetailFydoFragment extends Fragment {

    FragmentDataDetailFydoBinding binding;
    List<ApprovedFydo> approved;
    List<RejectedFydo> rejected;
    List<SubmittedFydo> submitted;
    String shopno;
    int position;


    public DataDetailFydoFragment newInstance(List<ApprovedFydo> list1, List<RejectedFydo> list2, List<SubmittedFydo> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailFydoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataDetailFydoBinding.inflate(inflater, container, false);
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
                            .remove(DataDetailFydoFragment.this).commit();
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
                                    binding.submittedFor.setText(submitted.get(position).getReportSubmittedFor());
//                                    binding.WorkingLocation.setText(submitted.get(position).());
                                    binding.fseName.setText(submitted.get(position).getFseName());
                                    binding.fseMobNo.setText(submitted.get(position).getFseMobile());
                                    binding.UserCustomerName.setText(submitted.get(position).getCustomerName());
                                    binding.userCustomerMobNumber.setText(submitted.get(position).getCustomerMobile());
                                    binding.teamLeaderName.setText(submitted.get(position).getTlName());

                                    Glide.with(getContext())
                                            .load(submitted.get(position).getShopPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.MYShopScreenshot);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getStoreFrontPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADStore);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getBankDetailsPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADBank);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getProfilePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADProfile);

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
                                    binding.submittedFor.setText(approved.get(position).getReportSubmittedFor());
//                                    binding.WorkingLocation.setText(submitted.get(position).());
                                    binding.fseName.setText(approved.get(position).getFseName());
                                    binding.fseMobNo.setText(approved.get(position).getFseMobile());
                                    binding.UserCustomerName.setText(approved.get(position).getCustomerName());
                                    binding.userCustomerMobNumber.setText(approved.get(position).getCustomerMobile());
                                    binding.teamLeaderName.setText(approved.get(position).getTlName());

                                    Glide.with(getContext())
                                            .load(approved.get(position).getShopPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.MYShopScreenshot);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getStoreFrontPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADStore);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getBankDetailsPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADBank);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getProfilePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADProfile);

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
                                    binding.submittedFor.setText(rejected.get(position).getReportSubmittedFor());
//                                    binding.WorkingLocation.setText(submitted.get(position).());
                                    binding.fseName.setText(rejected.get(position).getFseName());
                                    binding.fseMobNo.setText(rejected.get(position).getFseMobile());
                                    binding.UserCustomerName.setText(rejected.get(position).getCustomerName());
                                    binding.userCustomerMobNumber.setText(rejected.get(position).getCustomerMobile());
                                    binding.teamLeaderName.setText(rejected.get(position).getTlName());

                                    Glide.with(getContext())
                                            .load(rejected.get(position).getShopPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.MYShopScreenshot);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getStoreFrontPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADStore);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getBankDetailsPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADBank);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getProfilePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADProfile);

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