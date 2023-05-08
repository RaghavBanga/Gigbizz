package com.gigbiz.fragments.pwform;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.activity.JobSuccessActivity;
import com.gigbiz.databinding.FragmentOtherInfoPwBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.PwFinalFormRequest;
import com.gigbiz.models.PwFinalFormResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OtherInfoPwFragment extends Fragment {

    FragmentOtherInfoPwBinding binding;
    String report_id;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;
    FormDashBoardActivity formDashBoardActivity;
//
//    String[] employee_name = {"Aditya Srivastava",
//            "Aslam Khan",
//            "Gaurav Kumar Jha",
//            "Gopal Kumar",
//            "Indresh Suneja",
//            "Kaushal Kishore",
//            "Manish Kumar Shaw",
//            "Mayank Vijay",
//            "Mohd. Zaid",
//            "Nihal Singh",
//            "Rahul Singh",
//            "Ravi Kumar",
//            "Saurav",
//            "Shashank Shukla",
//            "Shubham Tripathi",
//            "Shubham Yadav",
//            "Subham chakraborty",
//            "Sunny Doriwal",
//            "Vikas Pandey",
//            "Prakash Maurya",
//            "Ayush Pandey",
//            "Deepak Bisht",
//            "Irfan Ali",
//            "Irfan Rashid",
//            "Manish Gupta",
//            "Priyanshu Ranjan",
//            "Himanshu Shekhar Singh",
//            "Rishabh Shukla",
//            "Kamlesh"};
//
//    String[] employee_id = {"PW5691",
//            "PW6006",
//            "PW5417",
//            "PW4237",
//            "PW5918",
//            "PW4254",
//            "PW4627",
//            "PW2956",
//            "PW4667",
//            "PW4240",
//            "PW4412",
//            "PW4301",
//            "PW4433",
//            "PW4023",
//            "PW4284",
//            "PW4253",
//            "PW5120",
//            "PW5599",
//            "PW4851",
//            "PW3431",
//            "PW4837",
//            "PW4657",
//            "PW5455",
//            "PW5641",
//            "PW5169",
//            "PW4702",
//            "PW6706",
//            "PW7178",
//            "PW6883"};

//   String employee_id_text, employee_name_text;

    public OtherInfoPwFragment() {
        // Required empty public constructor
    }

    public static OtherInfoPwFragment newInstance(String param1, String param2) {
        OtherInfoPwFragment fragment = new OtherInfoPwFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOtherInfoPwBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();

        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(), getActivity().getSharedPreferences("gigbiz", MODE_PRIVATE));


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainHelper.isValidEmail(binding.employeeEmail.getText().toString().trim()) == false) {
                    Toast.makeText(getContext(), "please enter correct email id", Toast.LENGTH_SHORT).show();
                }else if (binding.accountHolderName.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter Account holder Name", Toast.LENGTH_SHORT).show();
                }else if (binding.accountNo.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter Account number", Toast.LENGTH_SHORT).show();
                }else if (binding.ifscNo.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter IFSC code", Toast.LENGTH_SHORT).show();
                }else if (binding.panNo.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter Pan number", Toast.LENGTH_SHORT).show();
                }else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFinalReport(root);
                }

            }
        });

//        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, employee_name);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.employeeNameSpinner.setAdapter(ad);
//        binding.employeeNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                employee_name_text = employee_name[i];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        ArrayAdapter ad1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, employee_id);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.employeeIdSpin.setAdapter(ad1);
//        binding.employeeIdSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                employee_id_text = employee_id[i];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Call<PwFinalFormResponse> call = RetrofitClient.getInstance().getApi().getPwFinalFormResponse(
                        new PwFinalFormRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                MainHelper.getUserDataList(sh).get(0).getToken()
                                , report_id,
                                binding.accountNo.getText().toString().trim(),
                                binding.accountHolderName.getText().toString().trim(),
                                binding.bankName.getText().toString().trim(),
                                binding.ifscNo.getText().toString().trim(),
                                binding.panNo.getText().toString().trim(),
                                binding.gstNo.getText().toString().trim(),
                                binding.employeeEmail.getText().toString().trim(),
                                binding.employeeName.getText().toString().trim(),
                                binding.employeeId.getText().toString().trim()
                                ,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                        ));

                call.enqueue(new Callback<PwFinalFormResponse>() {
                    @Override
                    public void onResponse(Call<PwFinalFormResponse> call, Response<PwFinalFormResponse> response) {
                        PwFinalFormResponse submitAirtelReportThirdResponse = response.body();
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

                        }


                    }

                    @Override
                    public void onFailure(Call<PwFinalFormResponse> call, Throwable t) {
//                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        thread.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(OtherInfoAmazonViewModel.class);
    }
}