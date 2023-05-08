package com.gigbiz.fragments.shopinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentShopInfoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.helper.OtpBottomSheetDialog;
import com.gigbiz.models.OtpCheckRequest;
import com.gigbiz.models.OtpCheckResponse;
import com.gigbiz.models.OtpSendRequest;
import com.gigbiz.models.OtpSendResponse;
import com.gigbiz.models.Project;
import com.gigbiz.models.SubmitFirstReportRequest;
import com.gigbiz.models.SubmitFirstReportResponse;
import com.gigbiz.models.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInfoFragment extends Fragment {

    private ShopInfoViewModel mViewModel;
    private FragmentShopInfoBinding binding;
    private FormDashBoardActivity formDashBoardActivity;
    SharedPreferences sh;
    List<Project> list;
    List<UserData> userData;
    String citytext;
    private boolean check = false;
    private boolean check1 = false;
    OtpBottomSheetDialog bottomSheet;
    AlertDialog.Builder  alert;


    String[] category = {"kirana", "Medical shop/Clinics",
            "General store", "Fashion Retail shop", "Meat/ Dairy/Fruit/Vegs", "Events"};

    String[] city = {"New Delhi", "Mumbai",
            "Kolkata", "Meerut"};

    public static ShopInfoFragment newInstance() {
        return new ShopInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentShopInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
//        list = new ArrayList<>();
        userData = new ArrayList<>();
//        list = MainHelper.getUserTaskDetails(sh);
        userData = MainHelper.getUserDataList(sh);

        //spinner for category
        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.category.setAdapter(ad);
        binding.category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spinner for city
        ArrayAdapter ad1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, city);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.city.setAdapter(ad1);
        binding.city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                citytext = city[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (formDashBoardActivity.report_id == null){
                    Toast.makeText(getContext(), "first create your shop", Toast.LENGTH_SHORT).show();
                }else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitReportFirst();
                }
            }
        });

        binding.sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.shopeName.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                }else if (binding.phoneNumber.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                }else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    sendOtp();
                }
            }
        });

        getUserData();

        return root;
    }

    private void submitReportFirst() {

        //Todo have to code in background and change all field dynamically
        Call<SubmitFirstReportResponse> call = RetrofitClient.getInstance().getApi().getSubmitFirstReportResponse(new SubmitFirstReportRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                formDashBoardActivity.token, formDashBoardActivity.report_id,"1","New Delhi","110046","1",MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));

        call.enqueue(new Callback<SubmitFirstReportResponse>() {
            @Override
            public void onResponse(Call<SubmitFirstReportResponse> call, Response<SubmitFirstReportResponse> response) {
                SubmitFirstReportResponse submitFirstReportResponse = response.body();
                if (response.isSuccessful()) {
                    try {
                        check1=true;
                        Toast.makeText(getContext(), "Your shop is "+ submitFirstReportResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        binding.progressbar.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        check1=false;
                        Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                        binding.progressbar.setVisibility(View.GONE);

                    }

                }

                if (check1==true){
                    formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
                    formDashBoardActivity.binding.viewPager.setCurrentItem(1, true);
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                }


            }

            @Override
            public void onFailure(Call<SubmitFirstReportResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                binding.progressbar.setVisibility(View.GONE);

            }
        });
    }

    private void sendOtp() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<OtpSendResponse> call = RetrofitClient.getInstance().getApi().getOtpSendResponse(new OtpSendRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                        MainHelper.getUserDataList(sh).get(0).getToken(), formDashBoardActivity.project_id,binding.shopeName.getText().toString().trim(),
                        binding.phoneNumber.getText().toString().trim(),MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));

                call.enqueue(new Callback<OtpSendResponse>() {
                    @Override
                    public void onResponse(Call<OtpSendResponse> call, Response<OtpSendResponse> response) {
                        OtpSendResponse otpSendResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
//                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
//                                Toast.makeText(getContext(), "Your otp "+ otpSendResponse.getShopOtp(), Toast.LENGTH_SHORT).show();

                                if (otpSendResponse.getShopOtp() != null) {
                                    showAlertSMS(otpSendResponse.getShopOtp());
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            showOtpBottomSheet(otpSendResponse.getShopOtp(), otpSendResponse.getReportId());
                                            binding.progressbar.setVisibility(View.GONE);

                                        }
                                    }, 2000);
                                    check = true;
                                }else {
                                    Toast.makeText(getContext(), "otp is already created with this details", Toast.LENGTH_SHORT).show();
                                }
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

                        }


                    }

                    @Override
                    public void onFailure(Call<OtpSendResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.progressbar.setVisibility(View.GONE);

                    }
                });
            }
        });

        thread.start();


    }

    private void showAlertSMS(String shopOtp) {
        alert = new AlertDialog.Builder(getContext());
        alert.setTitle("OTP");
        alert.setMessage("Your OTP is "+shopOtp);

        alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alert.show();

    }

    private void showOtpBottomSheet(String shopOtp, String reportId) {

        bottomSheet = new OtpBottomSheetDialog(new OtpBottomSheetDialog.OtpVerify() {
            @Override
            public void onVerifyClick(String otp) {

                Call<OtpCheckResponse> call = RetrofitClient.getInstance().getApi().getOtpCheckResponse(new OtpCheckRequest(formDashBoardActivity.user_id,
                        formDashBoardActivity.token, reportId,otp,formDashBoardActivity.usertype.toLowerCase()));

                call.enqueue(new Callback<OtpCheckResponse>() {
                    @Override
                    public void onResponse(Call<OtpCheckResponse> call, Response<OtpCheckResponse> response) {
                        OtpCheckResponse otpCheckResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
                                Toast.makeText(getContext(), otpCheckResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                bottomSheet.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getContext(), "Otp not verified", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (check1==true){
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                        }


                    }

                    @Override
                    public void onFailure(Call<OtpCheckResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        bottomSheet.setCancelable(false);
        bottomSheet.show(getActivity().getSupportFragmentManager(), "ModalBottomSheet");
    }


    private void getUserData() {

        for (int i = 0; i < userData.size(); i++) {
            if (formDashBoardActivity.value.equals(userData.get(i).getProjectId())) {
                formDashBoardActivity.project_id = userData.get(i).getProjectId();
                formDashBoardActivity.user_id = userData.get(i).getUserId();
                formDashBoardActivity.usertype = userData.get(i).getType();
                formDashBoardActivity.token = userData.get(i).getToken();
            }
        }
//
//        for (int i = 0; i < list.size(); i++) {
//            if (formDashBoardActivity.value.equals(list.get(i).getProjectId())) {
//                project_id = list.get(i).getre();
//                user_id = list.get(i).getUserId();
//                usertype = list.get(i).getType();
//                token = list.get(i).getToken();
//            }
//        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShopInfoViewModel.class);
        // TODO: Use the ViewModel
    }

}