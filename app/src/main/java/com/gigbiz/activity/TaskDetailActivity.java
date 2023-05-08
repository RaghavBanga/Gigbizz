package com.gigbiz.activity;

import static com.gigbiz.DatabaseHandler.SharedPrefManger.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.ActivityTaskDetailBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.Project;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

import java.util.ArrayList;
import java.util.List;

public class TaskDetailActivity extends AppCompatActivity {

    private ActivityTaskDetailBinding binding;
    SharedPreferences sh;
    List<Project> list;
    String value, formtype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaskDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        sh = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        list = new ArrayList<>();
        list = MainHelper.getUserTaskDetails(sh);

        value = getIntent().getExtras().getString("key");
        formtype = getIntent().getExtras().getString(Utilities.FORM_TYPE);

        userValidate();
        checkInternetConnection();

        binding.onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainHelper.checkPermission(TaskDetailActivity.this)) {
                    //permission allowed
//                    Intent intent = new Intent(TaskDetailActivity.this, AddNewTaskActivity.class);
//                    intent.putExtra("key", value);
//                    intent.putExtra(Utilities.FORM_TYPE, formtype);
//                    startActivity(intent);

                    Intent intent = new Intent(TaskDetailActivity.this, LandingPage.class);
                    intent.putExtra("key", value);
                    intent.putExtra(Utilities.FORM_TYPE, formtype);
                    startActivity(intent);
                }
//        else{
//            //permission not allowed
//                MainHelper.requestPermission(TaskDetailActivity.this);
//
//        }

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


    private void userValidate() {

        for (int i = 0; i < list.size(); i++) {
            if (value.equals(list.get(i).getProjectId())) {
                binding.type.setText(list.get(i).getTrainingType());
                binding.compname.setText(list.get(i).getCompName());
                binding.projectStatus.setText(list.get(i).getProjectStatus());
                binding.trainingType.setText(list.get(i).getTrainingType());
                binding.education.setText(list.get(i).getEducation());
                binding.time.setText(list.get(i).getStartTime() + "-" + list.get(i).getEndTime());
                binding.termandcondition.setText(list.get(i).getTermsAndCondition());
                binding.price.setText("Rs " + list.get(i).getPriceStart() + "-" + " Rs " + list.get(i).getPriceEnd());
                Glide.with(getApplicationContext())
                        .load(list.get(i).getLogo())
                        .centerCrop()
                        .placeholder(R.drawable.logogigbiz)
                        .into(binding.logo);
            }
        }
    }
}