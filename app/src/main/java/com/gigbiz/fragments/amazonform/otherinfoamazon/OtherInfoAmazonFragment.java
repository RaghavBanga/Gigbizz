package com.gigbiz.fragments.amazonform.otherinfoamazon;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.gigbiz.databinding.FragmentOtherInfoAmazonBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.SubmitAmazonSecondRequest;
import com.gigbiz.models.SubmitAmazonSecondResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherInfoAmazonFragment extends Fragment {

    private OtherInfoAmazonViewModel mViewModel;
    FragmentOtherInfoAmazonBinding binding;
    String report_id;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;
    FormDashBoardActivity formDashBoardActivity;
    public RadioButton cosmosradioButton, amazonSellerRadioButton, adoptionRadioButton, calculatorRadioButton, fixedfeeRadioButton;

//    String[] user_permission = {"demoseller39@gmail.com", "anand1997kmtr@gmail.com",
//            "amazon.demoseller@gmail.com","info.sahjadg@gmail.com","listdemo1@gmail.com","braintaha123@gmail.com"
//            ,"sellerdemo.amazon@gmail.com","Other"};
//
//    String[] manager_name = {"ANAND PRAKASH", "Manish Jha",
//            "Raj Nagar", "Rishikesh", "Dindyal", "SHAHRUKH SHAIKH","RAVI KANT","Vikash kumar"
//            ,"Harsh shrivastav","Aman Srivastava","PAPPU KUMAR","Rajesh Kumar Singh","Sameer pathan"};

    String[] wssp_no = {"Yes (Rajni )+918586077804", "Yes(kanchan negi)+918586077231",
            "Yes(Bushra)+9839667741r", "Yes(Laxmi)+918586077928", "No"};

    String user_per_text, manager_name_text,wssp_no_text;

    public static OtherInfoAmazonFragment newInstance() {
        return new OtherInfoAmazonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherInfoAmazonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();

        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(), getActivity().getSharedPreferences("gigbiz", MODE_PRIVATE));


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainHelper.isValidPhoneNumber(binding.mobNumber.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct phone number", Toast.LENGTH_SHORT).show();
                }else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFinalReport(root);
                }


            }
        });

//        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, manager_name);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.managerSpin.setAdapter(ad);
//        binding.managerSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                manager_name_text = manager_name[i];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        ArrayAdapter ad1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, user_permission);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.userPerSpin.setAdapter(ad1);
//        binding.userPerSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                user_per_text = user_permission[i];
//                if (user_per_text.equals("Other")) {
//                    binding.other.setVisibility(View.VISIBLE);
//                } else {
//                    binding.other.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

        ArrayAdapter ad2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, wssp_no);
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.wsspNo.setAdapter(ad2);
        binding.wsspNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                wssp_no_text = wssp_no[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return root;
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

    private void submitFinalReport(View root) {
        int selectedId1 = binding.cosmosRadioGroup.getCheckedRadioButtonId();
        int selectedId2 = binding.amazonSellerRadioGroup.getCheckedRadioButtonId();
        int selectedId3 = binding.adoptionRadioGroup.getCheckedRadioButtonId();
        int selectedId4 = binding.calculatorRadioGroup.getCheckedRadioButtonId();
        cosmosradioButton = root.findViewById(selectedId1);
        amazonSellerRadioButton = root.findViewById(selectedId2);
        adoptionRadioButton = root.findViewById(selectedId3);
        calculatorRadioButton = root.findViewById(selectedId4);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (cosmosradioButton == null||amazonSellerRadioButton == null||adoptionRadioButton == null||calculatorRadioButton == null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }
                else {
                    Call<SubmitAmazonSecondResponse> call = RetrofitClient.getInstance().getApi().getAmazonSecondResponse(
                            new SubmitAmazonSecondRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                    MainHelper.getUserDataList(sh).get(0).getToken()
                                    , report_id
                                    ,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase(),
                                    binding.fosName.getText().toString().trim(),
                                    binding.mobNumber.getText().toString().trim(),
                                    binding.teamLeader.getText().toString().trim(),
                                    binding.ManagerName.getText().toString().trim(),
                                    binding.merchantTokenNo.getText().toString().trim(),
                                    binding.UserPermission.getText().toString().trim(),
                                    cosmosradioButton.getText().toString().trim(),
                                    amazonSellerRadioButton.getText().toString().trim(),
                                    adoptionRadioButton.getText().toString().toLowerCase().trim(),
                                    wssp_no_text,
                                    calculatorRadioButton.getText().toString().toLowerCase().trim()
                            ));

                    call.enqueue(new Callback<SubmitAmazonSecondResponse>() {
                        @Override
                        public void onResponse(Call<SubmitAmazonSecondResponse> call, Response<SubmitAmazonSecondResponse> response) {
                            SubmitAmazonSecondResponse submitAirtelReportThirdResponse = response.body();
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
                        public void onFailure(Call<SubmitAmazonSecondResponse> call, Throwable t) {
//                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

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
        mViewModel = new ViewModelProvider(this).get(OtherInfoAmazonViewModel.class);
        // TODO: Use the ViewModel
    }

}