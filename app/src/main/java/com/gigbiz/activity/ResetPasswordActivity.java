package com.gigbiz.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.ActivityResetPasswordBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.helper.OtpEditText;
import com.gigbiz.models.ChangePasswordOtp;
import com.gigbiz.models.ChangePasswordOtpResponse;
import com.gigbiz.models.ResetPasswordRequest;
import com.gigbiz.models.ResetPasswordResponse;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResetPasswordActivity extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;
    private boolean check = false;
    SharedPrefManger sharedPrefManger;
    String otp;
    OtpEditText otpEditText;
//    ProgressBar progressBarDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPrefManger = new SharedPrefManger(getApplicationContext(), getSharedPreferences(Utilities.APP_NAME, MODE_PRIVATE));

        checkInternetConnection();

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please Enter phone no.", Toast.LENGTH_SHORT).show();
                } else if (!binding.password.getText().toString().trim().equals(binding.confirmPass.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "password is not match", Toast.LENGTH_SHORT).show();
                } else if (binding.password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please Enter password", Toast.LENGTH_SHORT).show();
                } else if (binding.confirmPass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please Enter confirm password", Toast.LENGTH_SHORT).show();
                } else if (MainHelper.isValidEmail(binding.email.getText().toString().trim()) == false) {
                    Toast.makeText(getApplicationContext(), "Please Enter Correct Email", Toast.LENGTH_SHORT).show();
                } else {
                    binding.progressbar.setVisibility(View.VISIBLE);
//                    openDialogOtp(binding.phoneno.getText().toString(),binding.confirmPass.getText().toString());
                    changePasswordOtpCheck(binding.email.getText().toString(),binding.confirmPass.getText().toString());
                }
            }
        });

    }

    private void checkInternetConnection() {
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(
                this,
                getLifecycle()
        );

        DialogPropertiesPendulum properties = builder.getDialogProperties();

        properties.setConnectionCallback(new ConnectionCallback() { // Optional
            @Override
            public void hasActiveConnection(boolean hasActiveConnection) {
                // ...
            }
        });

        properties.setCancelable(false); // Optional
        properties.setNoInternetConnectionTitle("No Internet"); // Optional
        properties.setNoInternetConnectionMessage("Check your Internet connection and try again"); // Optional
        properties.setShowInternetOnButtons(true); // Optional
        properties.setPleaseTurnOnText("Please turn on"); // Optional
        properties.setWifiOnButtonText("Wifi"); // Optional
        properties.setMobileDataOnButtonText("Mobile data"); // Optional

        properties.setOnAirplaneModeTitle("No Internet"); // Optional
        properties.setOnAirplaneModeMessage("You have turned on the airplane mode."); // Optional
        properties.setPleaseTurnOffText("Please turn off"); // Optional
        properties.setAirplaneModeOffButtonText("Airplane mode"); // Optional
        properties.setShowAirplaneModeOffButtons(true); // Optional

        builder.build();
    }

    private void openDialogOtp(String email, String pass) {

        Dialog dialog = new Dialog(ResetPasswordActivity.this, R.style.CustomAlertDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.otp_screen_bottom_sheet);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(((MainHelper.getWidth(ResetPasswordActivity.this) / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        Button verify_btn = dialog.findViewById(R.id.verify);
        otpEditText = dialog.findViewById(R.id.otp);
//        progressBarDialog = dialog.findViewById(R.id.progressbar);

//        new OTP_Receiver().setEditText(otpEditText);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressBarDialog.setVisibility(View.VISIBLE);
                if (otp.equals(otpEditText.getText().toString().trim())) {
//                    changePasswordOtpCheck(phone,pass);
                    changePassword(email,pass,dialog);

                } else {
                    Toast.makeText(getApplicationContext(), "Otp Not Matched", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();

    }

    private void changePasswordOtpCheck(String email, String pass) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Call<ChangePasswordOtpResponse> call = RetrofitClient.getInstance().getApi().
                        getChangePasswordOtpResponse(new ChangePasswordOtp(
                                email));

                call.enqueue(new Callback<ChangePasswordOtpResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordOtpResponse> call, Response<ChangePasswordOtpResponse> response) {
                        ChangePasswordOtpResponse loginResponses = response.body();
                        if (response.isSuccessful()) {
                            if (loginResponses.getStatus() == 1) {
                                otp = loginResponses.getOtp().toString();
                                Toast.makeText(getApplicationContext(), loginResponses.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        openDialogOtp(email,pass);
                                    }
                                });

                            } else {
                                Toast.makeText(getApplicationContext(), loginResponses.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);
                            }


                        }

                        if (check == true) {

                        }


                    }

                    @Override
                    public void onFailure(Call<ChangePasswordOtpResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        binding.progressbar.setVisibility(View.GONE);

                    }
                });

            }
        });

        thread.start();

    }

    private void changePassword(String email, String pass, Dialog dialog) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Call<ResetPasswordResponse> call = RetrofitClient.getInstance().getApi().
                        getResetPasswordResponse(new ResetPasswordRequest(
                                email,
                                pass));

                call.enqueue(new Callback<ResetPasswordResponse>() {
                    @Override
                    public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                        ResetPasswordResponse loginResponses = response.body();
                        if (response.isSuccessful()) {
                            try {
                                Toast.makeText(getApplicationContext(), loginResponses.getMsg(), Toast.LENGTH_SHORT).show();
                                check = true;
//                                progressBarDialog.setVisibility(View.GONE);
                                dialog.dismiss();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                        finish();
                                    }
                                },1000);


                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                Toast.makeText(getApplicationContext(), "System Fail", Toast.LENGTH_SHORT).show();
//                                progressBarDialog.setVisibility(View.GONE);
                                dialog.dismiss();

                            }

                        }

                        if (check == true) {

                        }


                    }

                    @Override
                    public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressBarDialog.setVisibility(View.GONE);
//                        dialog.dismiss();

                    }
                });

            }
        });

        thread.start();

    }
}