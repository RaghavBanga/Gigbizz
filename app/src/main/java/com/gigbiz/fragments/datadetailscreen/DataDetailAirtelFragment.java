package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.databinding.FragmentDataDetailAirtelBinding;
import com.gigbiz.models.ApprovedAirtel;
import com.gigbiz.models.RejectedAirtel;
import com.gigbiz.models.SubmittedAirtel;

import java.util.List;


public class DataDetailAirtelFragment extends Fragment {

    FragmentDataDetailAirtelBinding binding;
    List<ApprovedAirtel> approved;
    List<RejectedAirtel> rejected;
    List<SubmittedAirtel> submitted;
    String shopno;
    int position;

    public DataDetailAirtelFragment newInstance(List<ApprovedAirtel> list1, List<RejectedAirtel> list2, List<SubmittedAirtel> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailAirtelFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDataDetailAirtelBinding.inflate(inflater, container, false);
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
                            .remove(DataDetailAirtelFragment.this).commit();
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
                                    binding.AgencyName.setText(submitted.get(position).getAgencyName());
                                    binding.enterMerchantName.setText(submitted.get(position).getShopName());
                                    binding.enterMerchantNo.setText(submitted.get(position).getShopMobile());
                                    binding.fseName.setText(submitted.get(position).getFseName());
                                    binding.fseMobNo.setText(submitted.get(position).getFseMobile());
                                    binding.TRANSACTIONDONE.setText(submitted.get(position).getTransDone());

                                    Glide.with(getContext())
                                            .load(submitted.get(position).getTransPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.TRANSACTIONSCREEN);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getUploadCodePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADQR);


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
                                    binding.AgencyName.setText(approved.get(position).getAgencyName());
                                    binding.enterMerchantName.setText(approved.get(position).getShopName());
                                    binding.enterMerchantNo.setText(approved.get(position).getShopMobile());
                                    binding.fseName.setText(approved.get(position).getFseName());
                                    binding.fseMobNo.setText(approved.get(position).getFseMobile());
                                    binding.TRANSACTIONDONE.setText(approved.get(position).getTransDone());

                                    Glide.with(getContext())
                                            .load(approved.get(position).getTransPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.TRANSACTIONSCREEN);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getUploadCodePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADQR);


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
                                    binding.AgencyName.setText(rejected.get(position).getAgencyName());
                                    binding.enterMerchantName.setText(rejected.get(position).getShopName());
                                    binding.enterMerchantNo.setText(rejected.get(position).getShopMobile());
                                    binding.fseName.setText(rejected.get(position).getFseName());
                                    binding.fseMobNo.setText(rejected.get(position).getFseMobile());
                                    binding.TRANSACTIONDONE.setText(rejected.get(position).getTransDone());

                                    Glide.with(getContext())
                                            .load(rejected.get(position).getTransPic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.TRANSACTIONSCREEN);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getUploadCodePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.UPLOADQR);


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