package com.gigbiz.fragments.bankdetails;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.HomeActivity;
import com.gigbiz.databinding.FragmentBankDetailBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.SendBankRequest;
import com.gigbiz.models.SendBankResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailFragment extends Fragment {

    private BankDetailViewModel mViewModel;
    private FragmentBankDetailBinding binding;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;
    String category[]={"Savings","Current"};
    String category_account_type;

    public static BankDetailFragment newInstance() {
        return new BankDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBankDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
        try {
            sharedPrefManger = new SharedPrefManger(getActivity(),sh);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.accountType.setAdapter(ad);
        binding.accountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category_account_type = category[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.bankName.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "please enter remaining details", Toast.LENGTH_SHORT).show();
                } else if (binding.accountholdername.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "please enter remaining details", Toast.LENGTH_SHORT).show();
                } else if (binding.accountNumber.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "please enter remaining details", Toast.LENGTH_SHORT).show();
                } else if (binding.ifscCode.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "please enter remaining details", Toast.LENGTH_SHORT).show();
                }
                else {
                    sendBankDetails();
                    binding.progressbar.setVisibility(View.VISIBLE);

                }

            }
        });

//        binding.back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    getFragmentManager().beginTransaction()
//                            .remove(BankDetailFragment.this).commit();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        return root;
    }

    private void sendBankDetails() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId  = MainHelper.getUserDataSignupList(sh).get(0).getUserId();
                String userToken  = MainHelper.getUserDataSignupList(sh).get(0).getToken();
                Call<SendBankResponse> call = RetrofitClient.getInstance().getApi().getBankDetailResponse(
                        new SendBankRequest(userId,
                                userToken,
                                binding.bankName.getText().toString().trim(),
                                binding.accountholdername.getText().toString().trim(),
                                binding.accountNumber.getText().toString().trim(),
                                category_account_type,
                                binding.ifscCode.getText().toString().trim()));

                call.enqueue(new Callback<SendBankResponse>() {
                    @Override
                    public void onResponse(Call<SendBankResponse> call, Response<SendBankResponse> response) {

                        SendBankResponse userTaskResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
                                Toast.makeText(getContext(), userTaskResponse.getMsg(), Toast.LENGTH_SHORT).show();

                                check=true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    check=false;
                                    binding.progressbar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "user not approved or System Fail", Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            }finally {

                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                binding.progressbar.setVisibility(View.GONE);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (check==true){
                                try {
                                    openKycSuccessDialog();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<SendBankResponse> call, Throwable t) {
                        binding.progressbar.setVisibility(View.GONE);
                    }
                });
            }
        });

        thread.start();
    }

    public void openKycSuccessDialog(){
        Dialog dialog = new Dialog(getContext(), R.style.CustomAlertDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.kyc_successful_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(((MainHelper.getWidth(getContext()) / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        TextView goBack = dialog.findViewById(R.id.done);
//        TextView cancel = dialog.findViewById(R.id.cancel);
//        ImageView cancel_btn = dialog.findViewById(R.id.imageView2);

//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

//        cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        dialog.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BankDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}