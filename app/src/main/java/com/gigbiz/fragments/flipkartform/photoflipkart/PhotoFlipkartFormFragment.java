package com.gigbiz.fragments.flipkartform.photoflipkart;

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
import com.gigbiz.databinding.FragmentPhotoFlipkartFormBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.MessageFromPhoto;
import com.gigbiz.models.SubmitFlipKartSecondRequest;
import com.gigbiz.models.SubmitFlipKartSecondResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFlipkartFormFragment extends Fragment {

    private PhotoFlipkartFormViewModel mViewModel;
    FragmentPhotoFlipkartFormBinding binding;
    File compressedImagebrand_cert;
    File compressedImage_register_img;
    File compressedImage_bank_ver;
    File compressedImage_store_pic;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    private boolean check = false;
    String report_id;int choice=0;


    public static PhotoFlipkartFormFragment newInstance() {
        return new PhotoFlipkartFormFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPhotoFlipkartFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        binding.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(MainHelper.checkPermission(getActivity())){
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
                    if(MainHelper.checkPermission(getActivity())){
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
                    if(MainHelper.checkPermission(getActivity())){
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
                    if(MainHelper.checkPermission(getActivity())){
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
                if (compressedImage_register_img == null){
                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
                }else if (compressedImage_bank_ver == null){
                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
                }else if (compressedImage_store_pic == null){
                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
                }
                else if (compressedImagebrand_cert == null){
                    choice=1;
                    binding.progressbar.setVisibility(View.VISIBLE);
                    sumbitSecondReport();
                }
                else {
                binding.progressbar.setVisibility(View.VISIBLE);
//                EventBus.getDefault().post(new MessageFromPhoto(gw_type));
                sumbitSecondReport();
                }
            }
        });


        return root;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        report_id= event.message;
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
                Call<SubmitFlipKartSecondResponse> call = RetrofitClient.getInstance()
                        .getApi().getFlipKartSecondResponse(
                                new SubmitFlipKartSecondRequest(
                                        MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken(),
                                        report_id
                                        ,choice==0?MainHelper.getFileToByte(compressedImagebrand_cert.getAbsolutePath()) : ""
                                        ,MainHelper.getFileToByte(compressedImage_register_img.getAbsolutePath())
                                        ,MainHelper.getFileToByte(compressedImage_bank_ver.getAbsolutePath())
                                        ,MainHelper.getFileToByte(compressedImage_store_pic.getAbsolutePath())
                                        ,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                ));

                call.enqueue(new Callback<SubmitFlipKartSecondResponse>() {
                    @Override
                    public void onResponse(Call<SubmitFlipKartSecondResponse> call, Response<SubmitFlipKartSecondResponse> response) {
                        SubmitFlipKartSecondResponse secondResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
                                check=true;
                                Toast.makeText(getContext(), secondResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);
//                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                check=false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            }

                        }

                        if (check==true){
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
                            formDashBoardActivity.binding.viewPager.setCurrentItem(2,true);
                            EventBus.getDefault().post(new MessageFromPhoto(formDashBoardActivity.report_id));

                        }


                    }

                    @Override
                    public void onFailure(Call<SubmitFlipKartSecondResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
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

                        compressedImagebrand_cert = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImagebrand_cert.getAbsolutePath())
                                .into(binding.brandCertImg);
                        Toast.makeText(getContext(), compressedImagebrand_cert.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }else if (requestCode == 2000) {
                    if (null != selectedImageUri) {

                        compressedImage_register_img = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_register_img.getAbsolutePath())
                                .into(binding.registrationScreenImg);
                        Toast.makeText(getContext(), compressedImage_register_img.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }else if (requestCode == 3000) {
                    if (null != selectedImageUri) {

                        compressedImage_bank_ver = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_bank_ver.getAbsolutePath())
                                .into(binding.bankVerImg);
                        Toast.makeText(getContext(), compressedImage_bank_ver.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }else if (requestCode == 4000) {
                    if (null != selectedImageUri) {

                        compressedImage_store_pic = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_store_pic.getAbsolutePath())
                                .into(binding.storePicImg);
                        Toast.makeText(getContext(), compressedImage_store_pic.getAbsolutePath(), Toast.LENGTH_SHORT).show();

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
        mViewModel = new ViewModelProvider(this).get(PhotoFlipkartFormViewModel.class);
        // TODO: Use the ViewModel
    }

}