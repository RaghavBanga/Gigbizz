package com.gigbiz.fragments.photo;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
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
import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentPhotoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.SubmitReportSecondRequest;
import com.gigbiz.models.SubmitReportSecondResponse;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFragment extends Fragment {

    private PhotoViewModel mViewModel;
    private FragmentPhotoBinding binding;
    private FormDashBoardActivity formDashBoardActivity;
    File compressedImage;
    File compressedImage1;
    private boolean check = false;
    SharedPreferences sh;
    SharedPrefManger sharedPrefManger;



    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(Utilities.APP_NAME, Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(),getActivity().getSharedPreferences(Utilities.APP_NAME,MODE_PRIVATE));

        try {
            formDashBoardActivity = (FormDashBoardActivity)getActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (formDashBoardActivity.report_id == null){
                    Toast.makeText(getContext(), "first create your shop", Toast.LENGTH_SHORT).show();
                }else if (compressedImage == null){
                    Toast.makeText(getContext(), "please select registration image", Toast.LENGTH_SHORT).show();
                }else if (compressedImage1 == null){
                    Toast.makeText(getContext(), "please select account detail image", Toast.LENGTH_SHORT).show();
                }else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    sumbitSendReport();
                }

            }
        });

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

        return root;
    }

    private void sumbitSendReport() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Call<SubmitReportSecondResponse> call = RetrofitClient.getInstance()
                        .getApi().getSubmitReportSecondResponse(new SubmitReportSecondRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                MainHelper.getUserDataList(sh).get(0).getToken(), formDashBoardActivity.report_id,MainHelper.getFileToByte(compressedImage.getAbsolutePath())
                                ,MainHelper.getFileToByte(compressedImage1.getAbsolutePath()),null,null,null,null
                                ,MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));

                call.enqueue(new Callback<SubmitReportSecondResponse>() {
                    @Override
                    public void onResponse(Call<SubmitReportSecondResponse> call, Response<SubmitReportSecondResponse> response) {
                        SubmitReportSecondResponse secondResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
//                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
//                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
                                Toast.makeText(getContext(), secondResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);
                                check=true;
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
                        }


                    }

                    @Override
                    public void onFailure(Call<SubmitReportSecondResponse> call, Throwable t) {
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                if (requestCode == 1000) {
                    if (null != selectedImageUri) {

                        compressedImage = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage.getAbsolutePath())
                                .into(binding.regisImg);
                        Toast.makeText(getContext(), compressedImage.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }else if (requestCode == 2000) {
                    if (null != selectedImageUri) {

                        compressedImage1 = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage1.getAbsolutePath())
                                .into(binding.accountImg);
                        Toast.makeText(getContext(), compressedImage1.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}