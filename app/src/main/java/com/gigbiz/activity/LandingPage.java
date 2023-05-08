package com.gigbiz.activity;

import static com.gigbiz.DatabaseHandler.SharedPrefManger.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.Utilities;
import com.gigbiz.adapters.LandingPageAdapter;
import com.gigbiz.databinding.ActivityLandingPageBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.Project;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

import java.util.ArrayList;
import java.util.List;

public class LandingPage extends AppCompatActivity {

    private ActivityLandingPageBinding binding;
    public String value, formtype, tittle, type_list, referral_url;
    List<Project> list;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        value = getIntent().getExtras().getString("key");
//        type_list = getIntent().getExtras().getString(Utilities.TYPE_LIST);
//        formtype = getIntent().getExtras().getString(Utilities.FORM_TYPE);

        sh = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        list = new ArrayList<>();


        checkInternetConnection();

//        setListAccordingToSelection();

        binding.onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        try {
            binding.btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (MainHelper.checkPermission(LandingPage.this)) {

                        if (formtype.equals("goodworker")) {
                            Intent intent = new Intent(LandingPage.this, FormDashBoardActivity.class);
                            intent.putExtra("key", value);
                            intent.putExtra("tittle", tittle);
                            intent.putExtra(Utilities.FORM_TYPE, formtype);
                            startActivity(intent);
                        } else if (formtype.equals("amazon")) {
                            Intent intent = new Intent(LandingPage.this, FormDashBoardActivity.class);
                            intent.putExtra("key", value);
                            intent.putExtra("tittle", tittle);
                            intent.putExtra(Utilities.FORM_TYPE, formtype);
                            startActivity(intent);
                        } else if (formtype.equals("airtel")) {
                            Intent intent = new Intent(LandingPage.this, FormDashBoardActivity.class);
                            intent.putExtra("key", value);
                            intent.putExtra("tittle", tittle);
                            intent.putExtra(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST);
                            intent.putExtra(Utilities.FORM_TYPE, formtype);
                            startActivity(intent);
                        } else if (formtype.equals("fydo")) {
                            Intent intent = new Intent(LandingPage.this, FormDashBoardActivity.class);
                            intent.putExtra("key", value);
                            intent.putExtra("tittle", tittle);
                            intent.putExtra(Utilities.FORM_TYPE, formtype);
                            startActivity(intent);
                        } else if (formtype.equals("flipkart")) {
                            Intent intent = new Intent(LandingPage.this, FormDashBoardActivity.class);
                            intent.putExtra("key", value);
                            intent.putExtra("tittle", tittle);
                            intent.putExtra(Utilities.FORM_TYPE, formtype);
                            startActivity(intent);
                        } else if (formtype.equals("pw")) {
                            Intent intent = new Intent(LandingPage.this, FormDashBoardActivity.class);
                            intent.putExtra("key", value);
                            intent.putExtra("tittle", tittle);
                            intent.putExtra(Utilities.FORM_TYPE, formtype);
                            startActivity(intent);
                        } else if (formtype.equals("apna")) {
                            Toast.makeText(getApplicationContext(), "Project is not Active", Toast.LENGTH_SHORT).show();
                        } else if (formtype.equals("more")) {
                            Toast.makeText(getApplicationContext(), "Project is not Active", Toast.LENGTH_SHORT).show();
                        } else if (formtype.equals("account")) {
//                            Toast.makeText(getApplicationContext(), "Project is not Active", Toast.LENGTH_SHORT).show();
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GigBiz");
                            String shareMessage = "\nHi\nDownload GigBiz application today and Earn Money on Completion of different task\nclick here to get it for android:-";
                            shareMessage = shareMessage + referral_url + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } else {
//                            Toast.makeText(getApplicationContext(), "Project is not Active", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LandingPage.this, FormDashBoardActivity.class);
                            intent.putExtra("key", value);
                            intent.putExtra("tittle", tittle);
                            intent.putExtra(Utilities.FORM_TYPE, formtype);
                            startActivity(intent);
                        }
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Earnings"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Description"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Training"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Media content"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_START);

//        TabLayoutUtils.enableTabs(binding.tabLayout, false );

        final LandingPageAdapter adapter = new LandingPageAdapter(this, getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(adapter);
//        binding.viewPager.setPagingEnabled(false);
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

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        value = event.message;
        type_list = event.list_type;
//        Toast.makeText(getActivity(), event.message+"Earning", Toast.LENGTH_SHORT).show();

        if (event.list_type.equals(Utilities.USER_TASK_DETAIL_FIELDS)) {
            list = MainHelper.getUserTaskDetails(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetails(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetails(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetails(sh).get(i).getHandler();
                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST)) {
            list = MainHelper.getUserTaskDetailsCREDIT_CARD(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsCREDIT_CARD(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsCREDIT_CARD(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsCREDIT_CARD(sh).get(i).getHandler();
                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh).get(i).getHandler();

                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsHOME_LOANS(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsHOME_LOANS(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsHOME_LOANS(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsHOME_LOANS(sh).get(i).getHandler();

                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh).get(i).getHandler();

                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsCAR_LOANS(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsCAR_LOANS(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsCAR_LOANS(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsCAR_LOANS(sh).get(i).getHandler();

                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_HEALTH_LIST)) {
            list = MainHelper.getUserTaskDetailsHEALTH(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsHEALTH(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsHEALTH(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsHEALTH(sh).get(i).getHandler();

                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_CAR_LIST)) {
            list = MainHelper.getUserTaskDetailsCAR(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsCAR(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsCAR(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsCAR(sh).get(i).getHandler();

                }
            }
        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_LIFE_LIST)) {
            list = MainHelper.getUserTaskDetailsLIFE(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsLIFE(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsLIFE(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsLIFE(sh).get(i).getHandler();

                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_SAVING_LIST)) {
            list = MainHelper.getUserTaskDetailsSAVING(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsSAVING(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsSAVING(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsSAVING(sh).get(i).getHandler();

                    if (list.get(i).getReferral_url() == null) {
                        referral_url = " ";
                    } else {
                        referral_url = list.get(i).getReferral_url();
                    }
                    binding.btnNext.setText("Share Link");

                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_DEMAT_LIST)) {
            list = MainHelper.getUserTaskDetailsDEMAT(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsDEMAT(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsDEMAT(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsDEMAT(sh).get(i).getHandler();

                    if (list.get(i).getReferral_url() == null) {
                        referral_url = " ";
                    } else {
                        referral_url = list.get(i).getReferral_url();
                    }

                    binding.btnNext.setText("Share Link");
                }
            }
        } else if (event.list_type.equals(Utilities.USER_TASK_DETAIL_MORE_LIST)) {
            list = MainHelper.getUserTaskDetailsMORE(sh);
            for (int i = 0; i < list.size(); i++) {
                if (value.equals(list.get(i).getProjectId())) {
                    binding.tittle.setText(MainHelper.getUserTaskDetailsMORE(sh).get(i).getProjectTitle());
                    tittle = MainHelper.getUserTaskDetailsMORE(sh).get(i).getProjectTitle();
                    formtype = MainHelper.getUserTaskDetailsMORE(sh).get(i).getHandler();

                }
            }
        }
//        Toast.makeText(getActivity(), event.message+"detail", Toast.LENGTH_SHORT).show();


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

//    private void setListAccordingToSelection() {
//        if (type_list.equals(Utilities.USER_TASK_DETAIL_FIELDS)) {
//            list = MainHelper.getUserTaskDetails(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetails(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetails(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST)) {
//            list = MainHelper.getUserTaskDetailsCREDIT_CARD(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsCREDIT_CARD(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsCREDIT_CARD(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsHOME_LOANS(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsHOME_LOANS(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsHOME_LOANS(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh).get(i).getProjectTitle();
//                }
//            }
//        }  else if (type_list.equals(Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsCAR_LOANS(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsCAR_LOANS(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsCAR_LOANS(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_HEALTH_LIST)) {
//            list = MainHelper.getUserTaskDetailsHEALTH(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsHEALTH(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsHEALTH(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_CAR_LIST)) {
//            list = MainHelper.getUserTaskDetailsCAR(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsCAR(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsCAR(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_LIFE_LIST)) {
//            list = MainHelper.getUserTaskDetailsLIFE(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsLIFE(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsLIFE(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_SAVING_LIST)) {
//            list = MainHelper.getUserTaskDetailsSAVING(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsSAVING(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsSAVING(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_DEMAT_LIST)) {
//            list = MainHelper.getUserTaskDetailsDEMAT(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsDEMAT(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsDEMAT(sh).get(i).getProjectTitle();
//                }
//            }
//        } else if (type_list.equals(Utilities.USER_TASK_DETAIL_MORE_LIST)) {
//            list = MainHelper.getUserTaskDetailsMORE(sh);
//            for (int i = 0; i < list.size(); i++) {
//                if (value.equals(list.get(i).getProjectId())) {
//                    binding.tittle.setText(MainHelper.getUserTaskDetailsMORE(sh).get(i).getProjectTitle());
//                    tittle = MainHelper.getUserTaskDetailsMORE(sh).get(i).getProjectTitle();
//                }
//            }
//        }
//    }

    private void userValidate() {

        for (int i = 0; i < list.size(); i++) {
            if (value.equals(list.get(i).getProjectId())) {
                binding.tittle.setText(MainHelper.getUserTaskDetails(sh).get(i).getProjectTitle());
                tittle = MainHelper.getUserTaskDetails(sh).get(i).getProjectTitle();
            }
        }
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