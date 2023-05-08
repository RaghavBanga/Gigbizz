package com.gigbiz.fragments.fydoform.photofydo;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentPhotoFydoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.MessageFromPhoto;
import com.gigbiz.models.SignUpType;
import com.gigbiz.models.SignUpTypefydOther;
import com.gigbiz.models.SubmitFydoSecondRequest;
import com.gigbiz.models.SubmitFydoSecondResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFydoFragment extends Fragment {

    private PhotoFydoViewModel mViewModel;
    FragmentPhotoFydoBinding binding;
    File compressedImage_shop_pic;
    File compressedImage_store_img;
    File compressedImage_bank_ver;
    File compressedImage_profile_pic;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    private boolean check = false;
    String report_id, type;

    public static PhotoFydoFragment newInstance() {
        return new PhotoFydoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPhotoFydoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        binding.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        //permission allowed
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1000);

                    }
//                    else{
//                        //permission not allowed
//                        MainHelper.requestPermission(getActivity());
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        //permission allowed
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2000);

                    }
//                    else{
//                        //permission not allowed
//                        MainHelper.requestPermission(getActivity());
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        //permission allowed
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3000);

                    }
//                    else{
//                        //permission not allowed
//                        MainHelper.requestPermission(getActivity());
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        //permission allowed
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 4000);

                    }
//                    else{
//                        //permission not allowed
//                        MainHelper.requestPermission(getActivity());
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (formDashBoardActivity.report_id == null){
//                    Toast.makeText(getContext(), "first create your shop", Toast.LENGTH_SHORT).show();
//                }
//                else if (compressedImage1 == null){
//                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
//                }else if (compressedImage2 == null){
//                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
//                }else if (compressedImage3 == null){
//                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
//                }else if (compressedImage4 == null){
//                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
//                }
//                else {
                binding.progressbar.setVisibility(View.VISIBLE);
//                EventBus.getDefault().post(new MessageFromPhoto(gw_type));
                sumbitSecondReport();
//                }
            }
        });


        return root;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSignUpType(SignUpType event) {
//        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
        type = event.message;
        if (event.message.equals("User (Customer)")) {
            binding.uploadProfileLay.setVisibility(View.VISIBLE);
            binding.shopLay.setVisibility(View.GONE);
            binding.storLay.setVisibility(View.GONE);
            binding.bankLay.setVisibility(View.GONE);
        } else {
            binding.uploadProfileLay.setVisibility(View.GONE);
            binding.shopLay.setVisibility(View.VISIBLE);
            binding.storLay.setVisibility(View.VISIBLE);
            binding.bankLay.setVisibility(View.VISIBLE);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        report_id = event.message;
//        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();

//        if (report_id.toLowerCase().trim().equals("employer")) {
//            binding.formTittle.setText("Employer");
//            binding.appliedJobScreenshotEmployee.setVisibility(View.GONE);
//        }else if (gw_type.toLowerCase().trim().equals("employee")){
//            binding.formTittle.setText("Employee");
//            binding.uploadDocumentsEmployer.setVisibility(View.GONE);
//            binding.hiringPostScreenshotEmployer.setVisibility(View.GONE);
//        }

    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void sumbitSecondReport() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (type.equals("User (Customer)")) {
                    Call<SubmitFydoSecondResponse> call = RetrofitClient.getInstance()
                            .getApi().getFydoReportSecondResponse(
                                    new SubmitFydoSecondRequest(
                                            MainHelper.getUserDataList(sh).get(0).getUserId(),
                                            MainHelper.getUserDataList(sh).get(0).getToken(),
                                            report_id
                                            , MainHelper.getFileToByte(compressedImage_profile_pic.getAbsolutePath())
                                            , null
                                            , null
                                            , null
                                            , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                    ));

                    call.enqueue(new Callback<SubmitFydoSecondResponse>() {
                        @Override
                        public void onResponse(Call<SubmitFydoSecondResponse> call, Response<SubmitFydoSecondResponse> response) {
                            SubmitFydoSecondResponse secondResponse = response.body();
                            if (response.isSuccessful()) {
                                try {
                                    check = true;
                                    Toast.makeText(getContext(), secondResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);
//                                }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    check = false;
                                    Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);

                                }

                            }

                            if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
                                formDashBoardActivity.binding.viewPager.setCurrentItem(2, true);
                                EventBus.getDefault().post(new MessageFromPhoto(formDashBoardActivity.report_id));
                                EventBus.getDefault().post(new SignUpTypefydOther(type));

                            }


                        }

                        @Override
                        public void onFailure(Call<SubmitFydoSecondResponse> call, Throwable t) {
                            try {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } else {
                    Call<SubmitFydoSecondResponse> call = RetrofitClient.getInstance()
                            .getApi().getFydoReportSecondResponse(
                                    new SubmitFydoSecondRequest(
                                            MainHelper.getUserDataList(sh).get(0).getUserId(),
                                            MainHelper.getUserDataList(sh).get(0).getToken(),
                                            report_id
                                            , null
                                            , MainHelper.getFileToByte(compressedImage_shop_pic.getAbsolutePath())
                                            , MainHelper.getFileToByte(compressedImage_store_img.getAbsolutePath())
                                            , MainHelper.getFileToByte(compressedImage_bank_ver.getAbsolutePath())
                                            , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                    ));

                    call.enqueue(new Callback<SubmitFydoSecondResponse>() {
                        @Override
                        public void onResponse(Call<SubmitFydoSecondResponse> call, Response<SubmitFydoSecondResponse> response) {
                            SubmitFydoSecondResponse secondResponse = response.body();
                            if (response.isSuccessful()) {
                                try {
                                    check = true;
                                    Toast.makeText(getContext(), secondResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);
//                                }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    check = false;
                                    Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);

                                }

                            }

                            if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
                                formDashBoardActivity.binding.viewPager.setCurrentItem(2, true);
                                EventBus.getDefault().post(new MessageFromPhoto(formDashBoardActivity.report_id));
                                EventBus.getDefault().post(new SignUpTypefydOther(type));


                            }


                        }

                        @Override
                        public void onFailure(Call<SubmitFydoSecondResponse> call, Throwable t) {
                            try {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

            }
        });

        thread.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                if (requestCode == 1000) {
                    if (null != selectedImageUri) {

                        compressedImage_shop_pic = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_shop_pic.getAbsolutePath())
                                .into(binding.shopPhotoImg);
                        Toast.makeText(getContext(), compressedImage_shop_pic.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 2000) {
                    if (null != selectedImageUri) {

                        compressedImage_store_img = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_store_img.getAbsolutePath())
                                .into(binding.storeFrontImg);
                        Toast.makeText(getContext(), compressedImage_store_img.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 3000) {
                    if (null != selectedImageUri) {

                        compressedImage_bank_ver = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_bank_ver.getAbsolutePath())
                                .into(binding.accountDetailImg);
                        Toast.makeText(getContext(), compressedImage_bank_ver.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 4000) {
                    if (null != selectedImageUri) {

                        compressedImage_profile_pic = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_profile_pic.getAbsolutePath())
                                .into(binding.profileImg);
                        Toast.makeText(getContext(), compressedImage_profile_pic.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PhotoFydoViewModel.class);
        // TODO: Use the ViewModel
    }

}