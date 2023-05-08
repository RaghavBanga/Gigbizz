package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gigbiz.databinding.FragmentDataDetailAmazonBinding;
import com.gigbiz.models.ApprovedAmazon;
import com.gigbiz.models.RejectedAmazon;
import com.gigbiz.models.SubmittedAmazon;

import java.util.List;


public class DataDetailAmazonFragment extends Fragment {

    FragmentDataDetailAmazonBinding binding;
    List<ApprovedAmazon> approved;
    List<RejectedAmazon> rejected;
    List<SubmittedAmazon> submitted;
    String shopno;
    int position;


    public DataDetailAmazonFragment newInstance(List<ApprovedAmazon> list1, List<RejectedAmazon> list2, List<SubmittedAmazon> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailAmazonFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataDetailAmazonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.tittle.setText(shopno);

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().beginTransaction()
                            .remove(DataDetailAmazonFragment.this).commit();
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
                                    binding.sellerStoreName.setText(submitted.get(position).getShopName());
                                    binding.sellerName.setText(submitted.get(position).getSellerName());
                                    binding.City.setText(submitted.get(position).getCity());
                                    binding.sellerMob.setText(submitted.get(position).getSellerMobile());
//                                    binding.RegisterMailId.setText(submitted.get(position).getma());
                                    binding.GSTNumber.setText(submitted.get(position).getGstNumber());
                                    binding.GSTStatus.setText(submitted.get(position).getGstStatus());
                                    binding.fosName.setText(submitted.get(position).getFosName());
                                    binding.mobNumber.setText(submitted.get(position).getFosMobile());
                                    binding.teamLeader.setText(submitted.get(position).getTlName());
                                    binding.ManagerName.setText(submitted.get(position).getManagerName());
                                    binding.merchantTokenNo.setText(submitted.get(position).getMerchantTokenNumber());
                                    binding.UserPermission.setText(submitted.get(position).getUserPermissionMailId());
                                    binding.CosmosId.setText(submitted.get(position).getCosmosId());
                                    binding.HaveYouCompleted.setText(submitted.get(position).getCompletedSellerOnboarding());
                                    binding.InteropAdoption.setText(submitted.get(position).getInteropAdoption());
                                    binding.wsspNo.setText(submitted.get(position).getProductSharedCatalogueTeam());
                                    binding.priceCalculator.setText(submitted.get(position).getPriceCalculator());

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if (approved!=null){

                        try {

                            binding.sellerStoreName.setText(approved.get(position).getShopName());
                            binding.sellerName.setText(approved.get(position).getSellerName());
                            binding.City.setText(approved.get(position).getCity());
                            binding.sellerMob.setText(approved.get(position).getSellerMobile());
//                                    binding.RegisterMailId.setText(submitted.get(position).getma());
                            binding.GSTNumber.setText(approved.get(position).getGstNumber());
                            binding.GSTStatus.setText(approved.get(position).getGstStatus());
                            binding.fosName.setText(approved.get(position).getFosName());
                            binding.mobNumber.setText(approved.get(position).getFosMobile());
                            binding.teamLeader.setText(approved.get(position).getTlName());
                            binding.ManagerName.setText(approved.get(position).getManagerName());
                            binding.merchantTokenNo.setText(approved.get(position).getMerchantTokenNumber());
                            binding.UserPermission.setText(approved.get(position).getUserPermissionMailId());
                            binding.CosmosId.setText(approved.get(position).getCosmosId());
                            binding.HaveYouCompleted.setText(approved.get(position).getCompletedSellerOnboarding());
                            binding.InteropAdoption.setText(approved.get(position).getInteropAdoption());
                            binding.wsspNo.setText(approved.get(position).getProductSharedCatalogueTeam());
                            binding.priceCalculator.setText(approved.get(position).getPriceCalculator());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else if (rejected!=null){
                        try {
                            binding.sellerStoreName.setText(rejected.get(position).getShopName());
                            binding.sellerName.setText(rejected.get(position).getSellerName());
                            binding.City.setText(rejected.get(position).getCity());
                            binding.sellerMob.setText(rejected.get(position).getSellerMobile());
//                                    binding.RegisterMailId.setText(submitted.get(position).getma());
                            binding.GSTNumber.setText(rejected.get(position).getGstNumber());
                            binding.GSTStatus.setText(rejected.get(position).getGstStatus());
                            binding.fosName.setText(rejected.get(position).getFosName());
                            binding.mobNumber.setText(rejected.get(position).getFosMobile());
                            binding.teamLeader.setText(rejected.get(position).getTlName());
                            binding.ManagerName.setText(rejected.get(position).getManagerName());
                            binding.merchantTokenNo.setText(rejected.get(position).getMerchantTokenNumber());
                            binding.UserPermission.setText(rejected.get(position).getUserPermissionMailId());
                            binding.CosmosId.setText(rejected.get(position).getCosmosId());
                            binding.HaveYouCompleted.setText(rejected.get(position).getCompletedSellerOnboarding());
                            binding.InteropAdoption.setText(rejected.get(position).getInteropAdoption());
                            binding.wsspNo.setText(rejected.get(position).getProductSharedCatalogueTeam());
                            binding.priceCalculator.setText(rejected.get(position).getPriceCalculator());

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