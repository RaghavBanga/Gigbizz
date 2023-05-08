package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.databinding.FragmentDataDetailFlipkartBinding;
import com.gigbiz.models.ApprovedFlipkart;
import com.gigbiz.models.RejectedFlipkart;
import com.gigbiz.models.SubmittedFlipkart;

import java.util.List;


public class DataDetailFlipkartFragment extends Fragment {

    FragmentDataDetailFlipkartBinding binding;
    List<ApprovedFlipkart> approved;
    List<RejectedFlipkart> rejected;
    List<SubmittedFlipkart> submitted;
    String shopno;
    int position;


    public DataDetailFlipkartFragment newInstance(List<ApprovedFlipkart> list1, List<RejectedFlipkart> list2, List<SubmittedFlipkart> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailFlipkartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDataDetailFlipkartBinding.inflate(inflater, container, false);
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
                            .remove(DataDetailFlipkartFragment.this).commit();
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
                                    binding.ShopCategory.setText(submitted.get(position).getShopCatid());
                                    binding.shopName.setText(submitted.get(position).getShopName());
                                    binding.supplierEmail.setText(submitted.get(position).getSupplierEmail());
                                    binding.pw.setText(submitted.get(position).getSupplierPassword());
                                    binding.supplierMobile.setText(submitted.get(position).getSupplierMobile());
                                    binding.supplierCity.setText(submitted.get(position).getSupplierCity());
                                    binding.supplierGst.setText(submitted.get(position).getSupplierGst());
                                    binding.supplierPin.setText(submitted.get(position).getSupplierPincode());
                                    binding.BDEName.setText(submitted.get(position).getBdeName());
                                    binding.teamLeader.setText(submitted.get(position).getTlName());
                                    binding.ManagerName.setText(submitted.get(position).getManagerName());
                                    binding.BDEMob.setText(submitted.get(position).getBdeMobileno());
                                    binding.BDEEmailID.setText(submitted.get(position).getBdeEmail());
                                    binding.productShared.setText(submitted.get(position).getProductSharedCatalogue());
                                    binding.collectionFee.setText(submitted.get(position).getCollectionFees());
                                    binding.FixedFees.setText(submitted.get(position).getFixedFees());
                                    binding.shippingFees.setText(submitted.get(position).getShippingFees());
                                    binding.ignite.setText(submitted.get(position).getSellerInterestedToSellProduct());

                                    Glide.with(getContext())
                                            .load(submitted.get(position).getBrandCertificates())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.BrandCertificates);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getRegistrationScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.RegistrationScreen);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getBankVerificationScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.BankVerification);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getStorePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.storePic);

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
                                    binding.ShopCategory.setText(approved.get(position).getShopCatid());
                                    binding.shopName.setText(approved.get(position).getShopName());
                                    binding.supplierEmail.setText(approved.get(position).getSupplierEmail());
                                    binding.pw.setText(approved.get(position).getSupplierPassword());
                                    binding.supplierMobile.setText(approved.get(position).getSupplierMobile());
                                    binding.supplierCity.setText(approved.get(position).getSupplierCity());
                                    binding.supplierGst.setText(approved.get(position).getSupplierGst());
                                    binding.supplierPin.setText(approved.get(position).getSupplierPincode());
                                    binding.BDEName.setText(approved.get(position).getBdeName());
                                    binding.teamLeader.setText(approved.get(position).getTlName());
                                    binding.ManagerName.setText(approved.get(position).getManagerName());
                                    binding.BDEMob.setText(approved.get(position).getBdeMobileno());
                                    binding.BDEEmailID.setText(approved.get(position).getBdeEmail());
                                    binding.productShared.setText(approved.get(position).getProductSharedCatalogue());
                                    binding.collectionFee.setText(approved.get(position).getCollectionFees());
                                    binding.FixedFees.setText(approved.get(position).getFixedFees());
                                    binding.shippingFees.setText(approved.get(position).getShippingFees());
                                    binding.ignite.setText(approved.get(position).getSellerInterestedToSellProduct());

                                    Glide.with(getContext())
                                            .load(approved.get(position).getBrandCertificates())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.BrandCertificates);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getRegistrationScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.RegistrationScreen);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getBankVerificationScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.BankVerification);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getStorePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.storePic);

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
                                    binding.ShopCategory.setText(rejected.get(position).getShopCatid());
                                    binding.shopName.setText(rejected.get(position).getShopName());
                                    binding.supplierEmail.setText(rejected.get(position).getSupplierEmail());
                                    binding.pw.setText(rejected.get(position).getSupplierPassword());
                                    binding.supplierMobile.setText(rejected.get(position).getSupplierMobile());
                                    binding.supplierCity.setText(rejected.get(position).getSupplierCity());
                                    binding.supplierGst.setText(rejected.get(position).getSupplierGst());
                                    binding.supplierPin.setText(rejected.get(position).getSupplierPincode());
                                    binding.BDEName.setText(rejected.get(position).getBdeName());
                                    binding.teamLeader.setText(rejected.get(position).getTlName());
                                    binding.ManagerName.setText(rejected.get(position).getManagerName());
                                    binding.BDEMob.setText(rejected.get(position).getBdeMobileno());
                                    binding.BDEEmailID.setText(rejected.get(position).getBdeEmail());
                                    binding.productShared.setText(rejected.get(position).getProductSharedCatalogue());
                                    binding.collectionFee.setText(rejected.get(position).getCollectionFees());
                                    binding.FixedFees.setText(rejected.get(position).getFixedFees());
                                    binding.shippingFees.setText(rejected.get(position).getShippingFees());
                                    binding.ignite.setText(rejected.get(position).getSellerInterestedToSellProduct());

                                    Glide.with(getContext())
                                            .load(rejected.get(position).getBrandCertificates())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.BrandCertificates);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getRegistrationScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.RegistrationScreen);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getBankVerificationScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.BankVerification);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getStorePic())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.storePic);

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