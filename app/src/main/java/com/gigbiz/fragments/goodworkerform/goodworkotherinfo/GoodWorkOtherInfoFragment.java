package com.gigbiz.fragments.goodworkerform.goodworkotherinfo;

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
import com.gigbiz.databinding.FragmentGoodWorkOtherInfoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.GoodWorkerFinalRequest;
import com.gigbiz.models.GoodWorkerFinalResponse;
import com.gigbiz.models.MessageForReportId;
import com.gigbiz.models.MessageFromPhoto;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodWorkOtherInfoFragment extends Fragment {

    private GoodWorkOtherInfoViewModel mViewModel;
    FragmentGoodWorkOtherInfoBinding binding;
    private FormDashBoardActivity formDashBoardActivity;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    String emp_code;
    String team_leader;
    String gw_type,reportid;
    private boolean check = false;

    public static GoodWorkOtherInfoFragment newInstance() {
        return new GoodWorkOtherInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentGoodWorkOtherInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(),getActivity().getSharedPreferences("gigbiz",MODE_PRIVATE));




        addListenerOnButton(root);

        return root;
    }

    private void addListenerOnButton(View root) {

        binding.next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    int selectedId1 = binding.radioGroup.getCheckedRadioButtonId();
                    int selectedId2 = binding.radioGroupTeam.getCheckedRadioButtonId();
                    radioButton1 = root.findViewById(selectedId1);
                    radioButton2 = root.findViewById(selectedId2);

                    if (radioButton1==null||radioButton2==null){
//                        Toast.makeText(getActivity(),"please select code and team leader",Toast.LENGTH_LONG).show();
                    }else {
                        emp_code = radioButton1.getText().toString().toLowerCase().trim();
                        team_leader = radioButton2.getText().toString().toLowerCase().trim();
                    }

                    if (MainHelper.isValidPhoneNumber(binding.fseNumber.getText().toString().trim()) == false){
                        Toast.makeText(getContext(), "Enter correct fse phone number", Toast.LENGTH_SHORT).show();
                    }
//                    else if (MainHelper.isValidPhoneNumber(binding.userMobileNo.getText().toString().trim()) == false){
//                        Toast.makeText(getContext(), "Enter correct User phone number", Toast.LENGTH_SHORT).show();
//                    }
                    else if (MainHelper.isValidPhoneNumber(binding.hrPersonMobileNo.getText().toString().trim()) == false){
                        Toast.makeText(getContext(), "Enter correct Hr phone number", Toast.LENGTH_SHORT).show();
                    }else {
                        binding.progressbar.setVisibility(View.VISIBLE);
                        sumbitFinalReport();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

    private void sumbitFinalReport() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    if (gw_type.toLowerCase().trim().equals("employee")) {
                        Call<GoodWorkerFinalResponse> call = RetrofitClient.getInstance()
                                .getApi().sendFinalFormGoodWorker(new GoodWorkerFinalRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken(),reportid,
                                        binding.fseName.getText().toString().trim(),
                                        binding.fseNumber.getText().toString().trim()
                                        ,team_leader!=null?team_leader:binding.otherTeamLeader.getText().toString().trim(),
//                                        binding.userName.getText().toString().trim(),
                                        "null",
                                        emp_code!=null?emp_code:binding.other.getText().toString().trim(),
//                                        binding.userMobileNo.getText().toString().trim(),
                                        "null",
                                        "null",
                                        "null",
                                        "null"
                                        ,MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));

                        call.enqueue(new Callback<GoodWorkerFinalResponse>() {
                            @Override
                            public void onResponse(Call<GoodWorkerFinalResponse> call, Response<GoodWorkerFinalResponse> response) {
                                GoodWorkerFinalResponse finalResponse = response.body();
                                if (response.isSuccessful()) {
                                    try {
                                        //                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
                                        //                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
                                        Toast.makeText(getContext(), finalResponse.getMsg(), Toast.LENGTH_SHORT).show();
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
                                    try {
                                        Intent intent = new Intent(getActivity(), JobSuccessActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("key1", finalResponse.getToken());
                                        intent.putExtras(bundle);
                                        getActivity().startActivity(intent);
                                        getActivity().finish();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }


                            }

                            @Override
                            public void onFailure(Call<GoodWorkerFinalResponse> call, Throwable t) {
                                try {
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }else{
                        Call<GoodWorkerFinalResponse> call = RetrofitClient.getInstance()
                                .getApi().sendFinalFormGoodWorker(new GoodWorkerFinalRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken(),formDashBoardActivity.report_id,
                                        binding.fseName.getText().toString().trim(),
                                        binding.fseNumber.getText().toString().trim()
                                        ,team_leader!=null?team_leader:binding.otherTeamLeader.getText().toString().trim(),
                                        "null",
                                        emp_code!=null?emp_code:binding.other.getText().toString().trim(),
                                        "null",
                                        binding.companyName.getText().toString().trim(),
                                        binding.hrPersonName.getText().toString().trim(),
                                        binding.hrPersonMobileNo.getText().toString().trim()
                                        ,MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));

                        call.enqueue(new Callback<GoodWorkerFinalResponse>() {
                            @Override
                            public void onResponse(Call<GoodWorkerFinalResponse> call, Response<GoodWorkerFinalResponse> response) {
                                GoodWorkerFinalResponse finalResponse = response.body();
                                if (response.isSuccessful()) {
                                    try {
                                        //                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
                                        //                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
                                        Toast.makeText(getContext(), finalResponse.getMsg(), Toast.LENGTH_SHORT).show();
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
                                    try {
                                        Intent intent = new Intent(getActivity(), JobSuccessActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("key1", finalResponse.getToken());
                                        intent.putExtras(bundle);
                                        getActivity().startActivity(intent);
                                        getActivity().finish();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }


                            }

                            @Override
                            public void onFailure(Call<GoodWorkerFinalResponse> call, Throwable t) {
                                try {
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageForReportId(MessageForReportId event) {
        reportid= event.message;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageFromPhoto(MessageFromPhoto event) {
        gw_type= event.message;
//        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();

        if (gw_type.toLowerCase().trim().equals("employer")) {
            binding.formTittle.setText("Employer");
//            binding.userNameLay.setVisibility(View.GONE);
//            binding.userMobileNoLay.setVisibility(View.GONE);
        }else if (gw_type.toLowerCase().trim().equals("employee")){
            binding.formTittle.setText("Employee");
            binding.companyNameLay.setVisibility(View.GONE);
            binding.hrPersonMobileNoLay.setVisibility(View.GONE);
            binding.hrPersonNameLay.setVisibility(View.GONE);
            binding.companyNameLay.setVisibility(View.GONE);



//            binding.uploadDocumentsEmployer.setVisibility(View.GONE);
//            binding.hiringPostScreenshotEmployer.setVisibility(View.GONE);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GoodWorkOtherInfoViewModel.class);
        // TODO: Use the ViewModel
    }

}