package com.gigbiz.fragments.flipkartform.otherinfoflipkart;

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
import com.gigbiz.databinding.FragmentOtherInfoFlipkartBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageFromPhoto;
import com.gigbiz.models.SubmitFlipKartFinalRequest;
import com.gigbiz.models.SubmitFlipKartFinalResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherInfoFlipkartFragment extends Fragment {

    private OtherInfoFlipkartViewModel mViewModel;
    FragmentOtherInfoFlipkartBinding binding;
    String[] bde_name = {"Raghunath", "Manish Kumar",
            "Shahebaz Kureshi", "Vartika Lal", "Maksud shaikh", "Azruddin Shaikh"
            , "Rahul kumar sinha", "Vikash kumar", "Harsh shrivastav", "Aman Srivastava",
            "PAPPU KUMAR", "Rajesh Kumar Singh", "Sameer pathan"};

    String[] manager_name = {"Manish Jha", "Vipin Thakur",
            "Anand Prakash", "Raj Nagar", "Dindayal/Dhantosh", "SHAHRUKH SHAIKH"};

//    String bde_name_text, manager_name_text;

    public RadioButton sellerInterestradioButton, collectionFeeRadioButton, productMrpRadioButton, shippingFeeRadioButton, fixedfeeRadioButton;
    String report_id;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;
    FormDashBoardActivity formDashBoardActivity;


    public static OtherInfoFlipkartFragment newInstance() {
        return new OtherInfoFlipkartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherInfoFlipkartBinding.inflate(inflater, container, false);
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

                if(MainHelper.isValidEmail(binding.BDEEmailID.getText().toString().trim()) == false) {
                    Toast.makeText(getContext(), "please enter correct email id", Toast.LENGTH_SHORT).show();
                }else if (MainHelper.isValidPhoneNumber(binding.BDEMob.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct phone number", Toast.LENGTH_SHORT).show();
                }else if ((binding.teamleaderName.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter BDE name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.ManagerName.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter BDE name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.BDEName.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter BDE name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.productMrpRadioGroup.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(getContext(), "please check all checkboxes are checked", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.collectionFeeRadioGroup.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(getContext(), "please check all checkboxes are checked", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.fixedFeeRadioGroup.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(getContext(), "please check all checkboxes are checked", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.sellerInterestRadioGroup.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(getContext(), "please check all checkboxes are checked", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.shippingFeeRadioGroup.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(getContext(), "please check all checkboxes are checked", Toast.LENGTH_SHORT).show();
                }
                else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFinalReport(root);
                }


            }
//        }
//    });
//
//    //spinner for bde name
//    ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, bde_name);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.bdeName.setAdapter(ad);
//        binding.bdeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            bde_name_text = bde_name[i];
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//    });
//
//    //spinner for manager name
//    ArrayAdapter ad1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, manager_name);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.manegerNameSpin.setAdapter(ad1);
//        binding.manegerNameSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            manager_name_text = manager_name[i];
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
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
        int selectedId1 = binding.sellerInterestRadioGroup.getCheckedRadioButtonId();
        int selectedId2 = binding.collectionFeeRadioGroup.getCheckedRadioButtonId();
        int selectedId3 = binding.productMrpRadioGroup.getCheckedRadioButtonId();
        int selectedId4 = binding.shippingFeeRadioGroup.getCheckedRadioButtonId();
        int selectedId5 = binding.fixedFeeRadioGroup.getCheckedRadioButtonId();
        sellerInterestradioButton = root.findViewById(selectedId1);
        collectionFeeRadioButton = root.findViewById(selectedId2);
        productMrpRadioButton = root.findViewById(selectedId3);
        shippingFeeRadioButton = root.findViewById(selectedId4);
        fixedfeeRadioButton = root.findViewById(selectedId5);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (sellerInterestradioButton == null||collectionFeeRadioButton == null||
                        productMrpRadioButton == null||shippingFeeRadioButton == null||fixedfeeRadioButton == null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }
                else {
                    Call<SubmitFlipKartFinalResponse> call = RetrofitClient.getInstance().getApi().getFlipKartFinalResponse(
                            new SubmitFlipKartFinalRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                    MainHelper.getUserDataList(sh).get(0).getToken()
                                    , report_id,
                                    binding.BDEName.getText().toString().trim(),
                                    binding.BDEMob.getText().toString().trim(),
                                    binding.teamleaderName.getText().toString().trim(),
                                    binding.ManagerName.getText().toString().trim(),
                                    binding.BDEEmailID.getText().toString().trim(),
                                    productMrpRadioButton.getText().toString().toLowerCase().trim(),
                                    collectionFeeRadioButton.getText().toString().toLowerCase().trim(),
                                    fixedfeeRadioButton.getText().toString().toLowerCase().trim(),
                                    shippingFeeRadioButton.getText().toString().toLowerCase().trim(),
                                    sellerInterestradioButton.getText().toString().toLowerCase().trim()
                                    , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()

                            ));

                    call.enqueue(new Callback<SubmitFlipKartFinalResponse>() {
                        @Override
                        public void onResponse(Call<SubmitFlipKartFinalResponse> call, Response<SubmitFlipKartFinalResponse> response) {
                            SubmitFlipKartFinalResponse submitAirtelReportThirdResponse = response.body();
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
                        public void onFailure(Call<SubmitFlipKartFinalResponse> call, Throwable t) {
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
        mViewModel = new ViewModelProvider(this).get(OtherInfoFlipkartViewModel.class);
        // TODO: Use the ViewModel
    }

}