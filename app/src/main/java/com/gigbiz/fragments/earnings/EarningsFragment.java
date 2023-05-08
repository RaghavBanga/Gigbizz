package com.gigbiz.fragments.earnings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.databinding.FragmentEarningsBinding;
import com.gigbiz.fragments.transactionhistory.TransactionHistoryFragment;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.RedeemRequest;
import com.gigbiz.models.RedeemResponse;
import com.gigbiz.models.WalletDetail;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EarningsFragment extends Fragment {

    private FragmentEarningsBinding binding;
    private boolean check = false;
    SharedPreferences sh;
    SharedPrefManger sharedPrefManger;
    public String totalIncome, balance, referral_url;
    Menu menu;

    public EarningsFragment() {
    }

    public static EarningsFragment newInstance(String param1, String param2) {
        EarningsFragment fragment = new EarningsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEarningsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(SharedPrefManger.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getActivity(), sh);

        binding.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    Uri uri = Uri.parse(String.valueOf(referral_url));
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GigBiz");
                    String shareMessage = "\nHi\nDownload GigBiz application today and Earn Money on Completion of different task\nclick here to get it for android:-";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.gigbiz" + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.transactionHistorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TransactionHistoryFragment transactionHistoryFragment = new TransactionHistoryFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container_earning_frag, transactionHistoryFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);// it will anim while calling fragment.
                    ft.addToBackStack(null); // it will manage back stack of fragments.
                    ft.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.reedem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialogRedeem();
                EarlySalaryFragment earlySalaryFragment = new EarlySalaryFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container_earning_frag, earlySalaryFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);// it will anim while calling fragment.
                ft.addToBackStack(null); // it will manage back stack of fragments.
                ft.commit();

            }
        });
        return root;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onWalletDetailEvent(WalletDetail event) {
        totalIncome = event.total;
        balance = event.balance;

        binding.totalIncome.setText(String.valueOf(event.total));
        binding.banlance.setText(String.valueOf(event.balance));

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        referral_url = event.message;

//        binding.referralUrl.setText(String.valueOf(event.message));

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

    private void sendRedeemRequest(TextView textView, ProgressBar progressBar, Dialog dialog) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<RedeemResponse> call = RetrofitClient.getInstance().getApi().getRedeemRequest(new
                        RedeemRequest(userId, userToken, textView.getText().toString().trim()));

                call.enqueue(new Callback<RedeemResponse>() {
                    @Override
                    public void onResponse(Call<RedeemResponse> call, Response<RedeemResponse> response) {
                        RedeemResponse profileDetailResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
//                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
//                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
                                check = true;
                                Toast.makeText(getContext(), String.valueOf(profileDetailResponse.getMsg()), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                dialog.dismiss();
//                                binding.name.setText(String.valueOf(profileDetailResponse.getName()));


                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }

                        }

                        if (check == true) {
                            //todo: have to reopen this line for change dynamically.
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                                    navigationView.getMenu().getItem(1).setTitle("Wallet("+String.valueOf(profileDetailResponse.getRemaningAmount())+")");

                                    TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
                                    textView.setText(String.valueOf(profileDetailResponse.getRemaningAmount()));

                                    binding.totalIncome.setText(String.valueOf(profileDetailResponse.getCurrentAmount()));
                                    binding.banlance.setText(String.valueOf(profileDetailResponse.getRemaningAmount()));
                                }
                            });


                        }


                    }

                    @Override
                    public void onFailure(Call<RedeemResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                });
            }
        });

        thread.start();
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        this.menu = menu;
//    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
//        getActivity().getMenuInflater().inflate(R.menu.home_other_option, menu);
        TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
        textView.setText(String.valueOf(balance));
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.activity_menu_item:
//                // Not implemented here
//                return false;
//            case R.id.fragment_menu_item:
//                // Do Fragment menu item stuff here
//                return true;
//            default:
//                break;
//        }
//
//        return false;
//    }

    private void openDialogRedeem() {

        Dialog dialog = new Dialog(getContext(), R.style.CustomAlertDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.reedem_dialog_box);
//        initFilterDialog(dialog);
        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(((getWidth(getContext()) / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

//        final Dialog dialog = new Dialog(getContext(),R.style.DialogTheme);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.reedem_dialog_box);

        TextView redeemNow = dialog.findViewById(R.id.redeemNow);
        TextView cancel = dialog.findViewById(R.id.cancel);
        TextView amount = dialog.findViewById(R.id.amount);
        ProgressBar progressBar = dialog.findViewById(R.id.progressbar);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        redeemNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                int value = Integer.parseInt(balance.replaceAll("[^0-9]", ""));
                if (amount.getText().toString().isEmpty()){
                    dialog.dismiss();
                    Toast.makeText(getContext(), "please enter amount", Toast.LENGTH_LONG).show();
                }else if (Integer.parseInt(amount.getText().toString()) > Integer.parseInt(String.valueOf(value))) {
//                    openSuccessDialog(false);
                    progressBar.setVisibility(View.GONE);
                    dialog.dismiss();
                    Toast.makeText(getContext(), "You might not have enough Balance in your Wallet to Withdraw. Check your account balance", Toast.LENGTH_SHORT).show();
                }else {
                    sendRedeemRequest(amount, progressBar, dialog);
                }
            }
        });

        dialog.show();

    }

    /**
     * currently this code is not in use it will use later
     * in next update  for showing unsuccessful and successful
     */
//    public void openSuccessDialog(boolean b){
//        Dialog dialog = new Dialog(getContext(), R.style.CustomAlertDialog);
//
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.kyc_successful_dialog);
//        dialog.setCancelable(false);
//        dialog.getWindow().setLayout(((MainHelper.getWidth(getContext()) / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setGravity(Gravity.CENTER);
//
//        TextView goBack = dialog.findViewById(R.id.done);
//        TextView successful = dialog.findViewById(R.id.successful);
//        TextView des = dialog.findViewById(R.id.description);
//        ImageView icon = dialog.findViewById(R.id.icon);
//
//        if (b){
//            successful.setText("Withdraw Successful");
//            des.setText("Your Request is Successfully send. You will get Your money in Your Register Bank Account");
//        }else {
//            successful.setText("Withdraw UnSuccessful");
//            successful.setTextColor(Color.RED);
//            des.setTextColor(Color.RED);
//            icon.setImageResource(R.drawable.unsuccessful);
//            des.setText("You might not have enough Balance in your Wallet to Withdraw. Check your account balance");
//        }
//
//        goBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_HISTORY);
//                getActivity().startActivity(intent);
//                getActivity().finish();
//            }
//        });
//
//        dialog.show();
//    }
    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

}