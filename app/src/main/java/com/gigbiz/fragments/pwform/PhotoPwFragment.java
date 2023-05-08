package com.gigbiz.fragments.pwform;

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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentPhotoPwBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.MessageFromPhoto;
import com.gigbiz.models.PwPhotoFormRequest;
import com.gigbiz.models.PwPhotoFormResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotoPwFragment extends Fragment {

    FragmentPhotoPwBinding binding;
    File compressedImage_shop_pic;
    File compressedImage_storeFront_img;
    File compressedImage_passbook_img;
    File compressedImage_pan_card;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    private boolean check = false;
    String report_id, type;
    public String projectId;


    public PhotoPwFragment() {
    }

    public static PhotoPwFragment newInstance(String param1, String param2) {
        PhotoPwFragment fragment = new PhotoPwFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotoPwBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        binding.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1000);

                    }
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
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2000);

                    }
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
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3000);

                    }
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
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 4000);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (formDashBoardActivity.report_id == null){
                    Toast.makeText(getContext(), "first create your shop", Toast.LENGTH_SHORT).show();
                }
                else if (compressedImage_shop_pic == null){
                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
                }else if (compressedImage_storeFront_img == null){
                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
                }else if (compressedImage_passbook_img == null){
                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
                }else if (compressedImage_pan_card == null){
                    Toast.makeText(getContext(), "please select image", Toast.LENGTH_SHORT).show();
                }
                else {
                binding.progressbar.setVisibility(View.VISIBLE);
                sumbitSecondReport();
                }
            }
        });


        return root;
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        projectId = event.message;
//        listtype = event.list_type;
//        Toast.makeText(getActivity(), event.message+"Earning", Toast.LENGTH_SHORT).show();
//
//        if (listtype.equals(Utilities.USER_TASK_DETAIL_FIELDS)) {
//            list = MainHelper.getUserTaskDetails(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST)) {
//            list = MainHelper.getUserTaskDetailsCREDIT_CARD(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsHOME_LOANS(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh);
//            userValidate();
//        }  else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsCAR_LOANS(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HEALTH_LIST)) {
//            list = MainHelper.getUserTaskDetailsHEALTH(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LIST)) {
//            list = MainHelper.getUserTaskDetailsCAR(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_LIFE_LIST)) {
//            list = MainHelper.getUserTaskDetailsLIFE(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_SAVING_LIST)) {
//            list = MainHelper.getUserTaskDetailsSAVING(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_DEMAT_LIST)) {
//            list = MainHelper.getUserTaskDetailsDEMAT(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_MORE_LIST)) {
//            list = MainHelper.getUserTaskDetailsMORE(sh);
//            userValidate();
//        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

        report_id = event.message;

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
                Call<PwPhotoFormResponse> call = RetrofitClient.getInstance()
                        .getApi().getPwPhotoFormResponse(
                                new PwPhotoFormRequest(
                                        MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken(),
                                        report_id
                                        , MainHelper.getFileToByte(compressedImage_shop_pic.getAbsolutePath())
                                        , MainHelper.getFileToByte(compressedImage_storeFront_img.getAbsolutePath())
                                        , MainHelper.getFileToByte(compressedImage_passbook_img.getAbsolutePath())
                                        , MainHelper.getFileToByte(compressedImage_pan_card.getAbsolutePath())
                                        , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                ));

                call.enqueue(new Callback<PwPhotoFormResponse>() {
                    @Override
                    public void onResponse(Call<PwPhotoFormResponse> call, Response<PwPhotoFormResponse> response) {
                        PwPhotoFormResponse secondResponse = response.body();
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

                        }


                    }

                    @Override
                    public void onFailure(Call<PwPhotoFormResponse> call, Throwable t) {
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

                        compressedImage_shop_pic = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_shop_pic.getAbsolutePath())
                                .into(binding.shopPhotoImg);
                        Toast.makeText(getContext(), compressedImage_shop_pic.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 2000) {
                    if (null != selectedImageUri) {

                        compressedImage_storeFront_img = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_storeFront_img.getAbsolutePath())
                                .into(binding.storeFrontImg);
                        Toast.makeText(getContext(), compressedImage_storeFront_img.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 3000) {
                    if (null != selectedImageUri) {

                        compressedImage_passbook_img = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_passbook_img.getAbsolutePath())
                                .into(binding.passbookImg);
                        Toast.makeText(getContext(), compressedImage_passbook_img.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 4000) {
                    if (null != selectedImageUri) {

                        compressedImage_pan_card = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedImage_pan_card.getAbsolutePath())
                                .into(binding.panCardImg);
                        Toast.makeText(getContext(), compressedImage_pan_card.getAbsolutePath(), Toast.LENGTH_SHORT).show();

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
//        mViewModel = new ViewModelProvider(this).get(PhotoFydoViewModel.class);
    }
}