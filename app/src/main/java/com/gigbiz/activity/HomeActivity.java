package com.gigbiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.R;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.ActivityHomeBinding;
import com.gigbiz.helper.MainHelper;
import com.google.android.material.navigation.NavigationView;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;


public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    DrawerLayout drawer;
    View hView;
    public String report_id;
    TextView nav_user,nav_user_phone;
    private String type;
    public NavController navController;
    public String totalIncome = "";
    public String balance = "";
    public boolean check = false;
    Menu menu;
    Menu menuNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
        sh = getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getApplicationContext(), sh);

        initViews();
        eventWork();
        checkInternetConnection();
        menuNav = binding.navView.getMenu();
        //getting type of user weather it employee or freelancer
        type = sharedPrefManger.getEmpTypeChoose();

        if (type != null) {
            if (type.equals(Utilities.TYPE_EMPLOYEE)) {
                getSupportActionBar().setTitle("Hi, " + String.valueOf(MainHelper.getUserDataList(sh).get(0).getName()));
                getSupportActionBar().setSubtitle(String.valueOf(MainHelper.getUserDataList(sh).get(0).getMobile()));

                Toast.makeText(getApplicationContext(), String.valueOf(MainHelper.getUserDataList(sh).get(0).getName()), Toast.LENGTH_SHORT).show();
                nav_user.setText("Welcome, " + String.valueOf(MainHelper.getUserDataList(sh).get(0).getName()));
                nav_user_phone.setText(String.valueOf(MainHelper.getUserDataList(sh).get(0).getMobile()));
                menuNav.getItem(4).setVisible(false);
            } else {
                getSupportActionBar().setTitle("Hi, " + String.valueOf(MainHelper.getUserDataList(sh).get(0).getName()));
                getSupportActionBar().setSubtitle(String.valueOf(MainHelper.getUserDataList(sh).get(0).getMobile()));

                Toast.makeText(getApplicationContext(), String.valueOf(MainHelper.getUserDataList(sh).get(0).getName()), Toast.LENGTH_SHORT).show();
                nav_user.setText("Welcome, " + String.valueOf(MainHelper.getUserDataList(sh).get(0).getName()));
                nav_user_phone.setText(String.valueOf(MainHelper.getUserDataList(sh).get(0).getMobile()));
                menuNav.getItem(4).setVisible(true);
            }
        }

//        getWalletDetail();


    }

//    private void getWalletDetail() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
//                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
//                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
//                Call<UserWalletDetailResponse> call = RetrofitClient.getInstance().getApi().getWalletRequest(
//                        new TransactionHistoryRequest(userId, userToken));
//
//                call.enqueue(new Callback<UserWalletDetailResponse>() {
//                    @Override
//                    public void onResponse(Call<UserWalletDetailResponse> call, Response<UserWalletDetailResponse> response) {
//
//                        UserWalletDetailResponse userWalletDetailResponse = response.body();
//                        if (response.isSuccessful()) {
//
//                            try {
////                                Toast.makeText(getApplicationContext(), userWalletDetailResponse.getRedeemList().get(0).getMsg(), Toast.LENGTH_SHORT).show();
////                                Toast.makeText(getApplicationContext(), userWalletDetailResponse.getRedeemList().get(0).getCurrentAmount(), Toast.LENGTH_SHORT).show();
////                                for (Redeem redeem:transactionHistoryResponse.getRedeemList()) {
////                                    redeemList.add(new Redeem(redeem.))
////                                }
//
//                                totalIncome = userWalletDetailResponse.getRedeemList().get(0).getCurrentAmount();
//                                balance = userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount();
//                                Toast.makeText(getApplicationContext(), balance, Toast.LENGTH_SHORT).show();
////                                Toast.makeText(getApplicationContext(), userWalletDetailResponse.getRedeemList().get(0).getReferral_url(), Toast.LENGTH_SHORT).show();
//
//                                check = true;
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                check = false;
//                                Toast.makeText(getApplicationContext(), "System Fail", Toast.LENGTH_SHORT).show();
//
//                            } finally {
//
////                                runOnUiThread(new Runnable() {
////                                    @Override
////                                    public void run() {
////                                        try {
////                                            if (userWalletDetailResponse.getRedeemList()!=null){
////                                                TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
////                                                textView.setText(String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount()+" Rs"));
////                                                binding.navView.getMenu().getItem(1).setTitle("Wallet("+String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount())+") Rs");
////                                                EventBus.getDefault().postSticky(new WalletDetail(totalIncome,balance));
////
//////                                                adapter.setNewList(redeemList);
//////                                                binding.progressbar.setVisibility(View.GONE);
////
////                                            }
////
////                                        } catch (Exception e) {
////                                            e.printStackTrace();
////                                        }
////
////                                    }
////                                });
//
//                            }
//
//                            if (check == true) {
//                                EventBus.getDefault().postSticky(new WalletDetail(totalIncome, balance));
//                                EventBus.getDefault().postSticky(new MessageEvent(userWalletDetailResponse.getRedeemList().get(0).getReferral_url()));
//
////                                sharedPrefManger.setListUserTaskDetails(transactionHistoryResponse.getProjects());
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            if (userWalletDetailResponse.getRedeemList() != null) {
//                                                TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
//                                                textView.setText(String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount()));
//                                                binding.navView.getMenu().getItem(1).setTitle("Wallet(" + String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount()) + ")");
////                                                EventBus.getDefault().postSticky(new WalletDetail(totalIncome,balance));
//
////                                                adapter.setNewList(redeemList);
////                                                binding.progressbar.setVisibility(View.GONE);
//
//                                            }
//
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//
//                                    }
//                                });
//                            }
//
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserWalletDetailResponse> call, Throwable t) {
//
//                    }
//                });
//            }
//        });
//
//        thread.start();
//    }

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

    private void eventWork() {
        /**
         * calling from job success activity
         * */
        if (getIntent().getBooleanExtra(Utilities.OPEN_REPORT_LIST, false)) {
            navController.navigate(R.id.reportListSelectionFragment);
        }
    }

    private void initViews() {
        drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        hView = navigationView.getHeaderView(0);
        nav_user = hView.findViewById(R.id.name);
        nav_user_phone = hView.findViewById(R.id.phoneno);

        hView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.close();
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mytask, R.id.myProfile, R.id.addnew_report,
                R.id.reportListFragment, R.id.reportListSelectionFragment, R.id.projectPayoutsFragment, R.id.teamReportFragment, R.id.earningsFragment)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.mytask:
                        navController.navigate(R.id.mytask);
                        break;
                    case R.id.myProfile:
                        navController.navigate(R.id.myProfile);
                        break;
//                    case R.id.addnew_report:
//                        navController.navigate(R.id.addnew_report);
//                        break;
                    case R.id.reportListFragment:
                        navController.navigate(R.id.reportListSelectionFragment);
                        break;
                    case R.id.teamProjectFragment:
                        navController.navigate(R.id.teamReportFragment);
                        break;
                    case R.id.projectPayoutsFragment:
                        navController.navigate(R.id.projectPayoutsFragment);
                        break;
                    case R.id.wallet:
                        navController.navigate(R.id.earningsFragment);
                        break;
                    case R.id.privacy_policy:
                        Uri uri = Uri.parse("https://www.gigbiz.in/privacy-policy-new.html"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.logout:
                        sharedPrefManger.logout();
//                        startActivity(new Intent(HomeActivity.this, SelectionScreen.class));
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        Toast.makeText(getApplicationContext(), "logout successful", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
//                    case R.id.goodwork:
//                        startActivity(new Intent(HomeActivity.this, GoodWorkFormScreen.class));
//                        break;
                    default:
                        return false;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_other_option, menu);
        this.menu = menu;

        menu.findItem(R.id.wallet_t).getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.earningsFragment);

            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
//        textView.setText(String.valueOf(balance));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

//            case R.id.wallet_:
//                break;
            case R.id.notification:
//                Toast.makeText(getApplicationContext(), "Cart is Working", Toast.LENGTH_SHORT).show();
                return true;
        }


        return super.onOptionsItemSelected(item);

    }


//    public List<UserData> getUserDataList() {
//        List<UserData> arrayItems = new ArrayList<>();
//        String serializedObject = sh.getString(Utilities.LOGIN_DATA_OF_USER, null);
//        if (serializedObject != null) {
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<UserData>>() {
//            }.getType();
//            arrayItems = gson.fromJson(serializedObject, type);
//        }
//
//        return arrayItems;
//    }


}