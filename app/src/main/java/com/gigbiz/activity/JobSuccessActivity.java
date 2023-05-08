package com.gigbiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.Utilities;
import com.gigbiz.databinding.ActivityJobSuccessBinding;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;


public class JobSuccessActivity extends AppCompatActivity {

    private ActivityJobSuccessBinding binding;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJobSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        value = getIntent().getExtras().getString("key1");
        binding.shopid.setText("ID " + value);

        checkInternetConnection();

        binding.submittedjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobSuccessActivity.this, HomeActivity.class);
                intent.putExtra(Utilities.OPEN_REPORT_LIST,true);
                startActivity(intent);
                finish();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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


    @Override
    public boolean onSupportNavigateUp() {
//        finish();
//        onBackPressed();
        Intent intent = new Intent(JobSuccessActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(JobSuccessActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}