package com.gigbiz.fragments.fydoform.otherfydo;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.activity.JobSuccessActivity;
import com.gigbiz.databinding.FragmentOtherInfoFydoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageFromPhoto;
import com.gigbiz.models.SignUpTypefydOther;
import com.gigbiz.models.SubmitFydoThirdRequest;
import com.gigbiz.models.SubmitFydoThirdResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherInfoFydoFragment extends Fragment {

    private OtherInfoFydoViewModel mViewModel;
    FragmentOtherInfoFydoBinding binding;
    String report_id,type;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;
    FormDashBoardActivity formDashBoardActivity;
    public RadioButton team_leader_radioButton;
    public static OtherInfoFydoFragment newInstance() {
        return new OtherInfoFydoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherInfoFydoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();

        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(), getActivity().getSharedPreferences("gigbiz", MODE_PRIVATE));


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (manager_name_text == null
//                        || bde_name_text == null) {
//
//                    Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
//
//                } else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFinalReport(root);

//                }
            }
        });

        return root;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSignUpTypefydOther(SignUpTypefydOther event) {
        type = event.message;
        if (event.message.equals("User (Customer)")) {
            binding.fseMobLay.setVisibility(View.VISIBLE);
            binding.fseNameLay.setVisibility(View.VISIBLE);
            binding.customerMobLay.setVisibility(View.VISIBLE);
            binding.customerNameLay.setVisibility(View.VISIBLE);
        }else{
            binding.fseMobLay.setVisibility(View.VISIBLE);
            binding.fseNameLay.setVisibility(View.VISIBLE);
            binding.customerMobLay.setVisibility(View.GONE);
            binding.customerNameLay.setVisibility(View.GONE);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageFromPhoto(MessageFromPhoto event) {
        report_id = event.message;
        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();

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


    private void submitFinalReport(View root) {
        int selectedId1 = binding.teamleaderRadioGroup.getCheckedRadioButtonId();
        team_leader_radioButton = root.findViewById(selectedId1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (team_leader_radioButton == null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }
                else {
                    if (type.equals("User (Customer)")) {
                        Call<SubmitFydoThirdResponse> call = RetrofitClient.getInstance().getApi().getFydoReportThirdResponse(
                                new SubmitFydoThirdRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken()
                                        , report_id,
                                        binding.fseName.getText().toString().trim(),
                                        binding.fseMobNo.getText().toString().trim(),
                                        team_leader_radioButton.getText().toString(),
                                        binding.UserCustomerName.getText().toString().trim(),
                                        binding.userCustomerMobNumber.getText().toString().trim()
                                        , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()

                                ));

                        call.enqueue(new Callback<SubmitFydoThirdResponse>() {
                            @Override
                            public void onResponse(Call<SubmitFydoThirdResponse> call, Response<SubmitFydoThirdResponse> response) {
                                SubmitFydoThirdResponse submitAirtelReportThirdResponse = response.body();
                                if (response.isSuccessful()) {
                                    try {
                                        check = true;
                                        binding.progressbar.setVisibility(View.GONE);
                                        Toast.makeText(getContext(), submitAirtelReportThirdResponse.getMsg(), Toast.LENGTH_SHORT).show();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        check = false;
                                        Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                                    }

                                }

                                if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    Intent intent = new Intent(getActivity(), JobSuccessActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("key1", submitAirtelReportThirdResponse.getToken());
                                    intent.putExtras(bundle);
                                    getActivity().startActivity(intent);
                                    getActivity().finish();

                                }


                            }

                            @Override
                            public void onFailure(Call<SubmitFydoThirdResponse> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }else{
                        Call<SubmitFydoThirdResponse> call = RetrofitClient.getInstance().getApi().getFydoReportThirdResponse(
                                new SubmitFydoThirdRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken()
                                        , report_id,
                                        binding.fseName.getText().toString().trim(),
                                        binding.fseMobNo.getText().toString().trim(),
                                        team_leader_radioButton.getText().toString(),
                                        null,
                                        null
                                        , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()

                                ));

                        call.enqueue(new Callback<SubmitFydoThirdResponse>() {
                            @Override
                            public void onResponse(Call<SubmitFydoThirdResponse> call, Response<SubmitFydoThirdResponse> response) {
                                SubmitFydoThirdResponse submitAirtelReportThirdResponse = response.body();
                                if (response.isSuccessful()) {
                                    try {
                                        check = true;
                                        binding.progressbar.setVisibility(View.GONE);
                                        Toast.makeText(getContext(), submitAirtelReportThirdResponse.getMsg(), Toast.LENGTH_SHORT).show();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        check = false;
                                        Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                                    }

                                }

                                if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    Intent intent = new Intent(getActivity(), JobSuccessActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("key1", submitAirtelReportThirdResponse.getToken());
                                    intent.putExtras(bundle);
                                    getActivity().startActivity(intent);
                                    getActivity().finish();
                                }


                            }

                            @Override
                            public void onFailure(Call<SubmitFydoThirdResponse> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }


            }
        });

        thread.start();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OtherInfoFydoViewModel.class);
        // TODO: Use the ViewModel
    }

}