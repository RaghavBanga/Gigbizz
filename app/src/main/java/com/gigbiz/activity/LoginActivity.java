package com.gigbiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.ActivityLoginBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.LoginRequest;
import com.gigbiz.models.LoginResponse;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
//    private String type;
    private boolean check = false;
    SharedPrefManger sharedPrefManger;
    RadioButton user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


//        type = getIntent().getStringExtra(Utilities.TYPE);
        sharedPrefManger = new SharedPrefManger(getApplicationContext(),getSharedPreferences(Utilities.APP_NAME,MODE_PRIVATE));

        if(sharedPrefManger.isFirstTimeLaunch()){
            sharedPrefManger.setFirstTimeLaunch(false);
            startActivity(new Intent(LoginActivity.this, IntroScreen.class));
            finish();
        }

        checkInternetConnection();

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
//                intent.putExtra(Utilities.TYPE,type);
                startActivity(intent);
            }
        });

        binding.forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
//                intent.putExtra(Utilities.TYPE,type);
                startActivity(intent);
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.phoneNumber.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "please fill phone number", Toast.LENGTH_SHORT).show();
                }else if (binding.Password.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "please fill password", Toast.LENGTH_SHORT).show();
                }else {
                    if (MainHelper.isValidPhoneNumber(binding.phoneNumber.getText().toString().trim())){
                        binding.progressbar.setVisibility(View.VISIBLE);
                        userLogin();
                    }else {
                        Toast.makeText(LoginActivity.this, "please enter correct phone number", Toast.LENGTH_SHORT).show();
                    }

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


    private void userLogin() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int selectedId1 = binding.teamleaderRadioGroup.getCheckedRadioButtonId();
                user_type = findViewById(selectedId1);
                if (user_type==null){
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }else {
                    Call<LoginResponse> call = RetrofitClient.getInstance().getApi().getLoginResponse(new LoginRequest(binding.phoneNumber.getText().toString().trim(),
                            binding.Password.getText().toString().trim(),
                            user_type.getText().toString().trim()=="Agency Partner"?"freelancer":"employee"));

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponses = response.body();
                            if (response.isSuccessful()) {
                                try {
                                    if (loginResponses.getUserData().get(0).getStatus() == 1) {
                                        Toast.makeText(getApplicationContext(), loginResponses.getUserData().get(0).getMsg(), Toast.LENGTH_SHORT).show();
//                                    check = true;
                                        binding.progressbar.setVisibility(View.GONE);
                                        sharedPrefManger.setEmpTypeChoose(loginResponses.getUserData().get(0).getType().toString().toLowerCase());

                                        sharedPrefManger.setListUserData(loginResponses.getUserData());
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        intent.putExtra(Utilities.TYPE,user_type.getText().toString().trim()=="Agency Partner"?"freelancer":"employee");
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        binding.progressbar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), loginResponses.getUserData().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
//                                check = false;
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);

                                }

                            }


                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }

            }
        });

        thread.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (sharedPrefManger.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}