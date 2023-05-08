package com.gigbiz.activity;

import static com.gigbiz.DatabaseHandler.SharedPrefManger.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.Utilities;
import com.gigbiz.adapters.TabLayoutFormAdapter;
import com.gigbiz.databinding.ActivityFormDashBoardBinding;
import com.gigbiz.helper.TabLayoutUtils;
import com.gigbiz.models.AllListHandler;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

public class FormDashBoardActivity extends AppCompatActivity{

    public ActivityFormDashBoardBinding binding;
    /**
     * spinner not in use currently
     */
//    String[] courses = { "Meezan Bank", "Alfalah",
//            "HBL", "UBL"};

    public String value,formtype,type_list;
    public String user_id;
    public String token;
    public String usertype;
    public String project_id;
    public String report_id;
    public String gw_type;
    public String tittle;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormDashBoardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sh = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        value = getIntent().getExtras().getString("key");
        tittle = getIntent().getExtras().getString("tittle");
//        type_list = getIntent().getExtras().getString(Utilities.TYPE_LIST);
        formtype = getIntent().getExtras().getString(Utilities.FORM_TYPE);

        binding.tittle.setText(tittle);


        checkInternetConnection();

//        if(MainHelper.checkPermission(FormDashBoardActivity.this)){
//            //permission allowed
//        }else{
//            //permission not allowed
//            MainHelper.requestPermission(FormDashBoardActivity.this);
//
//        }

        /**
         * spinner not in use currently
         */
//        binding.coursesspinner.setOnItemSelectedListener(this);
//
//        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);
//
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.coursesspinner.setAdapter(ad);

        binding.onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        if (formtype.equals("goodworker")){
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("SignUP"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Photos"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("OtherInfo"));
        }else if (formtype.equals("credit")){
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Details"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""));
        }else if (formtype.equals("loans")){
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Details"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""));
        }else if (formtype.equals("insurance")){
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Details"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(""));
        }else {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ShopInfo"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Photos"));
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("OtherInfo"));
        }
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("SHOPINFO"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Photos"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("OtherInfo"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_START);

        TabLayoutUtils.enableTabs(binding.tabLayout, false );

        final TabLayoutFormAdapter adapter = new TabLayoutFormAdapter(this,getSupportFragmentManager(), binding.tabLayout.getTabCount(),formtype,type_list);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setPagingEnabled(false);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //after removing this tabs will not work
                binding.viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        binding.viewPager.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View arg0, MotionEvent arg1) {
//                return true;
//            }
//        });




    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        value = event.message;
        type_list = event.list_type;
//        Toast.makeText(getApplicationContext(), event.list_type, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
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



    /**
     * spinner not in use currently
     */
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getApplicationContext(),
//                        courses[i],
//                        Toast.LENGTH_LONG)
//                .show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}