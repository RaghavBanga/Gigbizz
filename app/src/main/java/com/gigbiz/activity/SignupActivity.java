package com.gigbiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.ActivitySignupBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.LoginResponse;
import com.gigbiz.models.SignUpRequest;
import com.gigbiz.models.State;
import com.gigbiz.models.StateResponse;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    //    private String type;
    SharedPrefManger sharedPrefManger;
    private boolean check = false;
    RadioButton user_type;
    String selectedState;
    ArrayList<String> statelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        type = getIntent().getStringExtra(Utilities.TYPE);
        sharedPrefManger = new SharedPrefManger(getApplicationContext(), getSharedPreferences(Utilities.APP_NAME, MODE_PRIVATE));

        statelist = new ArrayList<>();

        checkInternetConnection();

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                intent.putExtra(Utilities.TYPE,type);
                startActivity(intent);

            }
        });

        binding.verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
//                startActivity(intent);
                if (binding.phoneNumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "please fill phone number", Toast.LENGTH_SHORT).show();
                } else if (binding.password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "please fill password", Toast.LENGTH_SHORT).show();
                } else if (binding.email.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "please fill email", Toast.LENGTH_SHORT).show();
                } else if (binding.city.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "please fill city", Toast.LENGTH_SHORT).show();
                } else if (binding.pinCode.getText().toString().trim().length()!=6) {
                    Toast.makeText(SignupActivity.this, "please fill Pin code", Toast.LENGTH_SHORT).show();
                } else if (selectedState.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "please fill state", Toast.LENGTH_SHORT).show();
                } else {
                    if (MainHelper.isValidPhoneNumber(binding.phoneNumber.getText().toString().trim()) == false) {
                        Toast.makeText(SignupActivity.this, "please enter correct phone number", Toast.LENGTH_SHORT).show();
                    } else if (MainHelper.isValidEmail(binding.email.getText().toString().trim())== false) {
                        Toast.makeText(SignupActivity.this, "please enter correct email address", Toast.LENGTH_SHORT).show();
                    } else {
                        binding.progressbar.setVisibility(View.VISIBLE);
                        userSignUp();
                    }

                }

            }
        });

        getStateList();

    }

    private void getStateList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Call<StateResponse> call = RetrofitClient.getInstance().getApi().getStateResponse();

                call.enqueue(new Callback<StateResponse>() {
                    @Override
                    public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                        StateResponse stateResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
                                for (State state : stateResponse.getStateList()) {
                                    statelist.add(state.getTitle());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter ad = new ArrayAdapter(SignupActivity.this, android.R.layout.simple_spinner_item, statelist);
                                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                binding.state.setAdapter(ad);
                                binding.state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        selectedState = statelist.get(i);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }
                        });

                        if (check == true) {

                        }


                    }

                    @Override
                    public void onFailure(Call<StateResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        thread.start();
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


    private void userSignUp() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //todo have to change signupResponse keyword with loginResponse
                int selectedId1 = binding.teamleaderRadioGroup.getCheckedRadioButtonId();
                user_type = findViewById(selectedId1);
                if (user_type == null) {
                    SignupActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SignupActivity.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                } else {
                    Call<LoginResponse> call = RetrofitClient.getInstance().getApi().getSignUpResponse(new SignUpRequest(binding.name.getText().toString().trim(),
                            binding.phoneNumber.getText().toString().trim(),
                            binding.password.getText().toString().trim(),
                            binding.email.getText().toString().trim(),
                            user_type.getText().toString().trim() == "Agency Partner" ? "freelancer" : "employee",
                            selectedState,
                            binding.city.getText().toString(),
                            binding.pinCode.getText().toString()
                    ));

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
                                        sharedPrefManger.setListUserData(loginResponses.getUserData());
                                        sharedPrefManger.setEmpTypeChoose(user_type.getText().toString().trim() == "Agency Partner" ? "freelancer" : "employee");
                                        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                        intent.putExtra(Utilities.TYPE, user_type.getText().toString().trim() == "Agency Partner" ? "freelancer" : "employee");
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        binding.progressbar.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), loginResponses.getUserData().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    check = false;
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    binding.progressbar.setVisibility(View.GONE);

                                }

                            }

                            if (check == true) {
//                                sharedPrefManger.setListUserData(loginResponses.getUserData());
//                                sharedPrefManger.setEmpTypeChoose(user_type.getText().toString().trim() == "Agency Partner" ? "freelancer" : "employee");
//                                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
//                                intent.putExtra(Utilities.TYPE, user_type.getText().toString().trim() == "Agency Partner" ? "freelancer" : "employee");
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
//                                startActivity(intent);
//                                finish();
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
}