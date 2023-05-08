package com.gigbiz.fragments.otherinfo;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.activity.JobSuccessActivity;
import com.gigbiz.databinding.FragmentOtherInfoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.SubmitReportFinalRequest;
import com.gigbiz.models.SubmitReportFinalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherInfoFragment extends Fragment {

    private OtherInfoViewModel mViewModel;
    private FragmentOtherInfoBinding binding;
    private FormDashBoardActivity formDashBoardActivity;
    private boolean check = false;
    SharedPreferences sh;
    SharedPrefManger sharedPrefManger;

    public static OtherInfoFragment newInstance() {
        return new OtherInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentOtherInfoBinding.inflate(inflater, container, false);
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
//                formDashBoardActivity.binding.viewPager.setCurrentItem(2,true);
//                if(binding.mrp.getText().toString().isEmpty()||binding.sellingprice.getText().toString().isEmpty()||binding.color.getText().toString().isEmpty()
//                        ||binding.material.getText().toString().isEmpty()||binding.size.getText().toString().isEmpty()
//                        ||binding.weight.getText().toString().isEmpty()||binding.quantity.getText().toString().isEmpty()
//                        ||binding.scdProductimg.getText().toString().isEmpty()||binding.productName.getText().toString().isEmpty()
//                        ||binding.mrpMop.getText().toString().isEmpty() || binding.sellingprice1.getText().toString().isEmpty()
//                        ||binding.color1.getText().toString().isEmpty()||binding.material1.getText().toString().isEmpty()
//                        ||binding.size1.getText().toString().isEmpty()||binding.weight1.getText().toString().isEmpty()
//                        ||binding.quantity1.getText().toString().isEmpty()){
//
//                    Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
//
//                }else if (formDashBoardActivity.report_id.isEmpty()){
//                    Toast.makeText(getContext(), "first create your shop", Toast.LENGTH_SHORT).show();
//                }else {
//                    submitFileReport();
//
//                }
                binding.progressbar.setVisibility(View.VISIBLE);
                submitFileReport();

            }
        });

        return root;
    }

    private void submitFileReport() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<SubmitReportFinalResponse> call = RetrofitClient.getInstance().getApi().getSubmitReportFinalResponse(new SubmitReportFinalRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                        MainHelper.getUserDataList(sh).get(0).getToken(), formDashBoardActivity.report_id,binding.mrp.getText().toString().trim(),
                        binding.sellingprice.getText().toString().trim(),binding.color.getText().toString().trim()
                        ,binding.material.getText().toString().trim(),binding.size.getText().toString().trim()
                        ,binding.weight.getText().toString().trim(),binding.quantity.getText().toString().trim()
                        ,binding.scdProductimg.getText().toString().trim(),binding.productName.getText().toString().trim()
                        ,binding.mrpMop.getText().toString().trim(),binding.sellingprice1.getText().toString().trim()
                        ,binding.color1.getText().toString().trim(),binding.material1.getText().toString().trim()
                        ,binding.size1.getText().toString().trim(),binding.weight1.getText().toString().trim()
                        ,binding.quantity1.getText().toString().trim(), MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));

                call.enqueue(new Callback<SubmitReportFinalResponse>() {
                    @Override
                    public void onResponse(Call<SubmitReportFinalResponse> call, Response<SubmitReportFinalResponse> response) {
                        SubmitReportFinalResponse otpSendResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
//                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
//                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
                                check=true;
                                Toast.makeText(getContext(), otpSendResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            } catch (Exception e) {
                                e.printStackTrace();
                                check=false;
                                binding.progressbar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (check==true){
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            try {
                                Intent intent = new Intent(getActivity(), JobSuccessActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("key1", otpSendResponse.getToken());
                                intent.putExtras(bundle);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }


                    }

                    @Override
                    public void onFailure(Call<SubmitReportFinalResponse> call, Throwable t) {
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
        mViewModel = new ViewModelProvider(this).get(OtherInfoViewModel.class);
        // TODO: Use the ViewModel
    }

}