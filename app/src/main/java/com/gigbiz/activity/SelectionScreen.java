package com.gigbiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.ActivitySelectionScreenBinding;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

public class SelectionScreen extends AppCompatActivity {

    private ActivitySelectionScreenBinding binding;
    private String type= Utilities.TYPE_FREELANCER;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectionScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sh = getSharedPreferences("asmaa", Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getApplicationContext(), sh);

//        if(sharedPrefManger.isFirstTimeLaunch()){
//            sharedPrefManger.setFirstTimeLaunch(false);
//            startActivity(new Intent(SelectionScreen.this, IntroScreen.class));
//            finish();
//        }

        checkInternetConnection();


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SelectionScreen.this, SignupActivity.class);
//                intent.putExtra(Utilities.TYPE,type);
//                startActivity(intent);

                if (type.equals(Utilities.TYPE_FREELANCER)){
                    sharedPrefManger.setEmpTypeChoose(type);
                    Intent intent = new Intent(SelectionScreen.this, SignupActivity.class);
                    intent.putExtra(Utilities.TYPE,type);
                    startActivity(intent);
                }else {
                    sharedPrefManger.setEmpTypeChoose(type);
                    Intent intent = new Intent(SelectionScreen.this, LoginActivity.class);
                    intent.putExtra(Utilities.TYPE,type);
                    startActivity(intent);
                }

            }
        });

        binding.freelancer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=Utilities.TYPE_FREELANCER;
                binding.freelancer.setVisibility(View.VISIBLE);
                binding.employee.setVisibility(View.GONE);
            }
        });

        binding.employee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type=Utilities.TYPE_EMPLOYEE;
                binding.freelancer.setVisibility(View.GONE);
                binding.employee.setVisibility(View.VISIBLE);
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
}