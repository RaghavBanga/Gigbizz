package com.gigbiz.fragments.goodworkerform.goodworkphoto;

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
import com.gigbiz.databinding.FragmentDocPhotoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.GoodWorkerSecondRequest;
import com.gigbiz.models.GoodWorkerSecondResponse;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.MessageForReportId;
import com.gigbiz.models.MessageFromPhoto;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocPhotoFragment extends Fragment {

    private DocPhotoViewModel mViewModel;
    FragmentDocPhotoBinding binding;
    File compressedImage1;
    File compressedImage2;
    File compressedImage3;
    File compressedImage4;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    private boolean check = false;
    String gw_type,reportid;

    public DocPhotoFragment() {
    }


//    public static DocPhotoFragment newInstance() {
//        return new DocPhotoFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDocPhotoBinding.inflate(inflater, container, false);
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
                    EventBus.getDefault().post(new MessageFromPhoto(gw_type));
                    sumbitSendReport();
//                }
            }
        });

        return root;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageForReportId(MessageForReportId event) {
        reportid= event.message;


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        gw_type= event.message;
//        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();

        if (gw_type.toLowerCase().trim().equals("employer")) {
            binding.formTittle.setText("Employer");
            binding.appliedJobScreenshotEmployee.setVisibility(View.GONE);
        }else if (gw_type.toLowerCase().trim().equals("employee")){
            binding.formTittle.setText("Employee");
            binding.uploadDocumentsEmployer.setVisibility(View.GONE);
            binding.hiringPostScreenshotEmployer.setVisibility(View.GONE);
        }

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

    @Override
    public void onResume() {
        super.onResume();

//        gw_type = sh.getString(Utilities.GOOD_WORKER_TYPE, null);



    }

    private void sumbitSendReport() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

//                    if (gw_type.toLowerCase().trim().equals("employee")) {
//                        Call<GoodWorkerSecondResponse> call = RetrofitClient.getInstance()
//                                .getApi().sendSecondFormGoodWorker(new GoodWorkerSecondRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
//                                        MainHelper.getUserDataList(sh).get(0).getToken(),formDashBoardActivity.report_id,MainHelper.getFileToByte(compressedImage4.getAbsolutePath())
//                                        ,MainHelper.getFileToByte(compressedImage3.getAbsolutePath()), null,
//                                        null
//                                        ,MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));
//
//                        call.enqueue(new Callback<GoodWorkerSecondResponse>() {
//                            @Override
//                            public void onResponse(Call<GoodWorkerSecondResponse> call, Response<GoodWorkerSecondResponse> response) {
//                                GoodWorkerSecondResponse secondResponse = response.body();
//                                if (response.isSuccessful()) {
//                                    try {
//                                        //                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
//                                        //                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
//                                        Toast.makeText(getContext(), secondResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                                        binding.progressbar.setVisibility(View.GONE);
//                                        check=true;
//                                        //                                }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                        check=false;
//                                        Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
//                                        binding.progressbar.setVisibility(View.GONE);
//
//                                    }
//
//                                }
//
//                                if (check==true){
//                                    //                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                                    //                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                                    formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
//                                    formDashBoardActivity.binding.viewPager.setCurrentItem(2,true);
//                                }
//
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<GoodWorkerSecondResponse> call, Throwable t) {
//                                try {
//                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                                    binding.progressbar.setVisibility(View.GONE);
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        });
//                    }else{
                        Call<GoodWorkerSecondResponse> call = RetrofitClient.getInstance()
                                .getApi().sendSecondFormGoodWorker(new GoodWorkerSecondRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken(),
                                        reportid,
                                        "null",
                                        compressedImage3 == null ? "null" : MainHelper.getFileToByte(compressedImage3.getAbsolutePath()),
                                        compressedImage1 == null ? "null" :MainHelper.getFileToByte(compressedImage1.getAbsolutePath()),
                                        compressedImage2 == null ? "null" :MainHelper.getFileToByte(compressedImage2.getAbsolutePath())
                                        ,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()));

                        call.enqueue(new Callback<GoodWorkerSecondResponse>() {
                            @Override
                            public void onResponse(Call<GoodWorkerSecondResponse> call, Response<GoodWorkerSecondResponse> response) {
                                GoodWorkerSecondResponse secondResponse = response.body();
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
                                    EventBus.getDefault().post(new MessageForReportId(secondResponse.getReportId()));

                                }


                            }

                            @Override
                            public void onFailure(Call<GoodWorkerSecondResponse> call, Throwable t) {
                                try {
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
//                    }

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
        mViewModel = new ViewModelProvider(this).get(DocPhotoViewModel.class);
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

                        compressedImage1 = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage1.getAbsolutePath())
                                .into(binding.uploadDoc);
                        Toast.makeText(getContext(), compressedImage1.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }else if (requestCode == 2000) {
                    if (null != selectedImageUri) {

                        compressedImage2 = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage2.getAbsolutePath())
                                .into(binding.regisImg);
                        Toast.makeText(getContext(), compressedImage2.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }else if (requestCode == 3000) {
                    if (null != selectedImageUri) {

                        compressedImage3 = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage3.getAbsolutePath())
                                .into(binding.accountImg);
                        Toast.makeText(getContext(), compressedImage3.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }else if (requestCode == 4000) {
                    if (null != selectedImageUri) {

                        compressedImage4 = new File(MainHelper.getRealPathFromURI(getActivity(),selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage4.getAbsolutePath())
                                .into(binding.regisImg1);
                        Toast.makeText(getContext(), compressedImage4.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}