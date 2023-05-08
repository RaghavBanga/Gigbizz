package com.gigbiz.fragments.datadetailscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.databinding.FragmentDataDetailSreenBinding;
import com.gigbiz.models.ApprovedGood;
import com.gigbiz.models.RejectedGood;
import com.gigbiz.models.SubmittedGood;

import java.util.List;

public class DataDetailScreenFragment extends Fragment {

    private DataDetailSreenViewModel mViewModel;
    FragmentDataDetailSreenBinding binding;
//    List<Approved> approved;
    List<ApprovedGood> approved;
//    List<Rejected> rejected;
    List<RejectedGood> rejected;
//    List<Submitted> submitted;
    List<SubmittedGood> submitted;
    String shopno;
    int position;


//    public DataDetailScreenFragment newInstance(List<Approved> list1, List<Rejected> list2, List<Submitted> list3, String shopeNo, int position) {
//        this.approved=list1;
//        this.rejected=list2;
//        this.submitted=list3;
//        this.shopno=shopeNo;
//        this.position=position;
//        return new DataDetailScreenFragment();
//    }

    public DataDetailScreenFragment newInstance(List<ApprovedGood> list1, List<RejectedGood> list2, List<SubmittedGood> list3, String shopeNo, int position) {
        this.approved=list1;
        this.rejected=list2;
        this.submitted=list3;
        this.shopno=shopeNo;
        this.position=position;
        return new DataDetailScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDataDetailSreenBinding.inflate(inflater, container, false);
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
                            .remove(DataDetailScreenFragment.this).commit();
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
//                    if (submitted!=null){
//                        try {
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    binding.shopName.setText(submitted.get(position).getShopName());
//                                    binding.merchantPhoneNumber.setText(submitted.get(position).getShopPhone());
//                                    binding.shopcat.setText(submitted.get(position).getCatName());
//                                    binding.shopPinCode.setText(submitted.get(position).getPincode());
//                                    Glide.with(getContext())
//                                            .load(submitted.get(position).getQrcodePicPrimary())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic2);
//                                    Glide.with(getContext())
//                                            .load(submitted.get(position).getQrcodePicSecondary())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic3);
//                                    Glide.with(getContext())
//                                            .load(submitted.get(position).getTentCardPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic4);
//                                    Glide.with(getContext())
//                                            .load(submitted.get(position).getSmallStickerPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic5);
//                                    Glide.with(getContext())
//                                            .load(submitted.get(position).getLargeStickerPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic6);
//                                    Glide.with(getContext())
//                                            .load(submitted.get(position).getHangingCollectralPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic1);
//
//                                    //            binding.qrUsedByMerchant.setText(sb.getShopName());
//                                    binding.noOfTentCard.setText(submitted.get(position).getNoOfTentCard());
//                                    binding.noOfA4.setText(submitted.get(position).getNoOfAfourPoster());
//                                    binding.noOfSmallStickers.setText(submitted.get(position).getNoOfSmallSticker());
//                                    binding.noOfTrad.setText(submitted.get(position).getNoOfTraditionalDangler());
//                                    binding.noOfBunting.setText(submitted.get(position).getNoOfBunting());
//                                    //            binding.noOfSmallStickers.setText(sb.getNoOfSmallSticker());
//
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }else if (approved!=null){
//
//                        try {
//
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                }
//                            });
//
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    binding.shopName.setText(approved.get(position).getShopName());
//                                    binding.merchantPhoneNumber.setText(approved.get(position).getShopPhone());
//                                    binding.shopcat.setText(approved.get(position).getCatName());
//                                    binding.shopPinCode.setText(approved.get(position).getPincode());
//                                    Glide.with(getContext())
//                                            .load(approved.get(position).getQrcodePicPrimary())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic2);
//                                    Glide.with(getContext())
//                                            .load(approved.get(position).getQrcodePicSecondary())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic3);
//                                    Glide.with(getContext())
//                                            .load(approved.get(position).getTentCardPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic4);
//                                    Glide.with(getContext())
//                                            .load(approved.get(position).getSmallStickerPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic5);
//                                    Glide.with(getContext())
//                                            .load(approved.get(position).getLargeStickerPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic6);
//                                    Glide.with(getContext())
//                                            .load(approved.get(position).getHangingCollectralPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic1);
//
//                                    //            binding.qrUsedByMerchant.setText(sb.getShopName());
//                                    binding.noOfTentCard.setText(approved.get(position).getNoOfTentCard());
//                                    binding.noOfA4.setText(approved.get(position).getNoOfAfourPoster());
//                                    binding.noOfSmallStickers.setText(approved.get(position).getNoOfSmallSticker());
//                                    binding.noOfTrad.setText(approved.get(position).getNoOfTraditionalDangler());
//                                    binding.noOfBunting.setText(approved.get(position).getNoOfBunting());
//    //            binding.noOfSmallStickers.setText(sb.getNoOfSmallSticker());
//
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }else if (rejected!=null){
//                        try {
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    binding.shopName.setText(rejected.get(position).getShopName());
//                                    binding.merchantPhoneNumber.setText(rejected.get(position).getShopPhone());
//                                    binding.shopcat.setText(rejected.get(position).getCatName());
//                                    binding.shopPinCode.setText(rejected.get(position).getPincode());
//                                    Glide.with(getContext())
//                                            .load(rejected.get(position).getQrcodePicPrimary())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic2);
//                                    Glide.with(getContext())
//                                            .load(rejected.get(position).getQrcodePicSecondary())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic3);
//                                    Glide.with(getContext())
//                                            .load(rejected.get(position).getTentCardPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic4);
//                                    Glide.with(getContext())
//                                            .load(rejected.get(position).getSmallStickerPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic5);
//                                    Glide.with(getContext())
//                                            .load(rejected.get(position).getLargeStickerPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic6);
//                                    Glide.with(getContext())
//                                            .load(rejected.get(position).getHangingCollectralPic())
//                                            .centerCrop()
//                                            .placeholder(R.drawable.logogigbiz)
//                                            .into(binding.pic1);
//
//                                    //            binding.qrUsedByMerchant.setText(sb.getShopName());
//                                    binding.noOfTentCard.setText(rejected.get(position).getNoOfTentCard());
//                                    binding.noOfA4.setText(rejected.get(position).getNoOfAfourPoster());
//                                    binding.noOfSmallStickers.setText(rejected.get(position).getNoOfSmallSticker());
//                                    binding.noOfTrad.setText(rejected.get(position).getNoOfTraditionalDangler());
//                                    binding.noOfBunting.setText(rejected.get(position).getNoOfBunting());
//    //            binding.noOfSmallStickers.setText(sb.getNoOfSmallSticker());
//
//                                }
//                            });
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }

                    if (submitted!=null){
                        try {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.gwType.setText(submitted.get(position).getGwType());
                                    binding.gwFseName.setText(submitted.get(position).getGwFseName());
                                    binding.gwFseMobile.setText(submitted.get(position).getGwFseMobile());
                                    binding.gwTeamleaderName.setText(submitted.get(position).getGwTeamleaderName());
                                    binding.gwEmpName.setText(submitted.get(position).getGwEmpName());
                                    binding.gwEmpCode.setText(submitted.get(position).getGwEmpCode());
                                    binding.gwCompanyName.setText(submitted.get(position).getGwCompanyName());
                                    binding.gwHrName.setText(submitted.get(position).getGwHrName());
                                    binding.gwHrRegisterName.setText(submitted.get(position).getGwHrRegisterName());

                                    if (submitted.get(position).getGwType().toLowerCase().trim().equals("employee")){
                                        binding.gwCompanyNameLay.setVisibility(View.GONE);
                                        binding.gwHrNameLay.setVisibility(View.GONE);
                                        binding.gwHrRegisterNameLay.setVisibility(View.GONE);
                                        binding.gwHiringPostScreenshotLay.setVisibility(View.GONE);
                                        binding.pic1GoodUploadLay.setVisibility(View.GONE);
                                    }else {
                                        binding.gwEmpCodeLay.setVisibility(View.GONE);
                                        binding.gwEmpNameLay.setVisibility(View.GONE);
                                        binding.gwEmpMobileLay.setVisibility(View.GONE);
                                        binding.gwAppliedJobScreenshotLay.setVisibility(View.GONE);
                                    }
                                    binding.gwEmpMobile.setText(submitted.get(position).getGwEmpMobile());

                                    Glide.with(getContext())
                                            .load(submitted.get(position).getGwUploadDocument())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.pic1GoodUpload);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getGwHiringPostScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwHiringPostScreenshot);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getGwSelfie())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwSelfie);
                                    Glide.with(getContext())
                                            .load(submitted.get(position).getGwAppliedJobScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwAppliedJobScreenshot);

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
                                    binding.gwType.setText(approved.get(position).getGwType());
                                    binding.gwFseName.setText(approved.get(position).getGwFseName());
                                    binding.gwFseMobile.setText(approved.get(position).getGwFseMobile());
                                    binding.gwTeamleaderName.setText(approved.get(position).getGwTeamleaderName());
                                    binding.gwEmpName.setText(approved.get(position).getGwEmpName());
                                    binding.gwEmpCode.setText(approved.get(position).getGwEmpCode());
                                    binding.gwEmpMobile.setText(approved.get(position).getGwEmpMobile());
                                    binding.gwCompanyName.setText(approved.get(position).getGwCompanyName());
                                    binding.gwHrName.setText(approved.get(position).getGwHrName());
                                    binding.gwHrRegisterName.setText(approved.get(position).getGwHrRegisterName());

                                    Glide.with(getContext())
                                            .load(approved.get(position).getGwUploadDocument())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.pic1GoodUpload);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getGwHiringPostScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwHiringPostScreenshot);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getGwSelfie())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwSelfie);
                                    Glide.with(getContext())
                                            .load(approved.get(position).getGwAppliedJobScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwAppliedJobScreenshot);

                                    if (submitted.get(position).getGwType().toLowerCase().trim().equals("employee")){
                                        binding.gwCompanyNameLay.setVisibility(View.GONE);
                                        binding.gwHrNameLay.setVisibility(View.GONE);
                                        binding.gwHrRegisterNameLay.setVisibility(View.GONE);
                                        binding.gwHiringPostScreenshotLay.setVisibility(View.GONE);
                                        binding.pic1GoodUploadLay.setVisibility(View.GONE);
                                    }else {
                                        binding.gwEmpCodeLay.setVisibility(View.GONE);
                                        binding.gwEmpNameLay.setVisibility(View.GONE);
                                        binding.gwEmpMobileLay.setVisibility(View.GONE);
                                        binding.gwAppliedJobScreenshotLay.setVisibility(View.GONE);
                                    }

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
                                    binding.gwType.setText(rejected.get(position).getGwFseName());
                                    binding.gwFseName.setText(rejected.get(position).getGwFseName());
                                    binding.gwFseMobile.setText(rejected.get(position).getGwFseMobile());
                                    binding.gwTeamleaderName.setText(rejected.get(position).getGwTeamleaderName());
                                    binding.gwEmpName.setText(rejected.get(position).getGwEmpName());
                                    binding.gwEmpCode.setText(rejected.get(position).getGwEmpCode());
                                    binding.gwEmpMobile.setText(rejected.get(position).getGwEmpMobile());
                                    binding.gwCompanyName.setText(rejected.get(position).getGwCompanyName());
                                    binding.gwHrName.setText(rejected.get(position).getGwHrName());
                                    binding.gwHrRegisterName.setText(rejected.get(position).getGwHrRegisterName());

                                    Glide.with(getContext())
                                            .load(rejected.get(position).getGwUploadDocument())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.pic1GoodUpload);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getGwHiringPostScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwHiringPostScreenshot);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getGwSelfie())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwSelfie);
                                    Glide.with(getContext())
                                            .load(rejected.get(position).getGwAppliedJobScreenshot())
                                            .centerCrop()
                                            .placeholder(R.drawable.logogigbiz)
                                            .into(binding.gwAppliedJobScreenshot);

                                    if (submitted.get(position).getGwType().toLowerCase().trim().equals("employee")){
                                        binding.gwCompanyNameLay.setVisibility(View.GONE);
                                        binding.gwHrNameLay.setVisibility(View.GONE);
                                        binding.gwHrRegisterNameLay.setVisibility(View.GONE);
                                        binding.gwHiringPostScreenshotLay.setVisibility(View.GONE);
                                        binding.pic1GoodUploadLay.setVisibility(View.GONE);
                                    }else {
                                        binding.gwEmpCodeLay.setVisibility(View.GONE);
                                        binding.gwEmpNameLay.setVisibility(View.GONE);
                                        binding.gwEmpMobileLay.setVisibility(View.GONE);
                                        binding.gwAppliedJobScreenshotLay.setVisibility(View.GONE);
                                    }

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
        mViewModel = new ViewModelProvider(this).get(DataDetailSreenViewModel.class);
        // TODO: Use the ViewModel
    }

}