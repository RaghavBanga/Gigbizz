package com.gigbiz.fragments.airtelform.otherinfoairtel;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.activity.JobSuccessActivity;
import com.gigbiz.databinding.FragmentOtherInfoAirtelBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageFromPhoto;
import com.gigbiz.models.SubmitAirtelReportThirdRequest;
import com.gigbiz.models.SubmitAirtelReportThirdResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherInfoAirtelFragment extends Fragment {

    private OtherInfoAirtelViewModel mViewModel;
    FragmentOtherInfoAirtelBinding binding;

    public RadioButton radioButton;
    String report_id;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;
    FormDashBoardActivity formDashBoardActivity;

    public static OtherInfoAirtelFragment newInstance() {
        return new OtherInfoAirtelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherInfoAirtelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();

        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(), getActivity().getSharedPreferences("gigbiz", MODE_PRIVATE));

        binding.rsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId1 = binding.rsRadioGroup.getCheckedRadioButtonId();
                radioButton = root.findViewById(selectedId1);
                if(radioButton.isChecked())
                {
                    radioButton.setChecked(false);
                }
                else
                {
                    radioButton.setChecked(true);
                }
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (manager_name_text == null
//                        || bde_name_text == null) {
//
//                    Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
//
//                } else {
                if (MainHelper.isValidPhoneNumber(binding.fseMobNo.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "Enter correct fse phone number", Toast.LENGTH_SHORT).show();
                }
                else if (binding.fseName.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please Enter name ", Toast.LENGTH_SHORT).show();
                }
                else if (binding.rsRadioGroup.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getContext(), "Please check checkbox ", Toast.LENGTH_SHORT).show();
                }
                else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFinalReport(root);
                }


//                }
            }
        });

        return root;
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
        int selectedId1 = binding.rsRadioGroup.getCheckedRadioButtonId();
        radioButton = root.findViewById(selectedId1);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (radioButton == null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                } else {
                    Call<SubmitAirtelReportThirdResponse> call = RetrofitClient.getInstance().getApi().getAirtelReportThirdResponse(
                            new SubmitAirtelReportThirdRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                    MainHelper.getUserDataList(sh).get(0).getToken()
                                    , report_id,
                                    binding.fseName.getText().toString().trim(),
                                    binding.fseMobNo.getText().toString().trim(),
                                    radioButton.getText().toString().trim()
                                    , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()

                            ));

                    call.enqueue(new Callback<SubmitAirtelReportThirdResponse>() {
                        @Override
                        public void onResponse(Call<SubmitAirtelReportThirdResponse> call, Response<SubmitAirtelReportThirdResponse> response) {
                            SubmitAirtelReportThirdResponse submitAirtelReportThirdResponse = response.body();
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
                        public void onFailure(Call<SubmitAirtelReportThirdResponse> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });

        thread.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OtherInfoAirtelViewModel.class);
        // TODO: Use the ViewModel
    }

}