package com.gigbiz.fragments.projectpayoutreport;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.adapters.ProjectPayoutAdapter;
import com.gigbiz.databinding.FragmentProjectPayoutsBinding;
import com.gigbiz.fragments.mytask.MyTaskFragment;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.PaymentModel;
import com.gigbiz.models.PayoutReportRequest;
import com.gigbiz.models.PayoutReportResponse;
import com.gigbiz.models.Project_Report;
import com.gigbiz.models.UserData;
import com.gigbiz.models.WalletDetail;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProjectPayoutsFragment extends Fragment {

    private FragmentProjectPayoutsBinding binding;
    List<PaymentModel> paymentlist=new ArrayList<>();
    ProjectPayoutAdapter adapter;
    SharedPreferences sh;List<UserData> userData;
    List<Project_Report> reports;
    public String totalIncome, balance, referral_url;
    Menu menu;

    public ProjectPayoutsFragment() {
        // Required empty public constructor
    }


    public static ProjectPayoutsFragment newInstance(String param1, String param2) {
        ProjectPayoutsFragment fragment = new ProjectPayoutsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProjectPayoutsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);

        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        userData = new ArrayList<>();
        reports = new ArrayList<>();
        userData = MainHelper.getUserDataList(sh);

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment_content_home, new MyTaskFragment()).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getPayoutReport();
        setRecyclerView();
        return root;
    }

    private void getPayoutReport() {
        Call<PayoutReportResponse> call = RetrofitClient.getInstance().getApi().getPayoutReportResponse(
                new PayoutReportRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                        MainHelper.getUserDataList(sh).get(0).getToken()
                        ,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                ));
//        Call<PayoutReportResponse> call = RetrofitClient.getInstance().getApi().getPayoutReportResponse(
//                new PayoutReportRequest("MTk4OQ==","4f1143fd9fd520eb6b09a53adde00a39","employee"));

        call.enqueue(new Callback<PayoutReportResponse>() {
            @Override
            public void onResponse(Call<PayoutReportResponse> call, Response<PayoutReportResponse> response) {
                PayoutReportResponse payoutReportResponse = response.body();
                if (response.isSuccessful()) {
                    try {

                        reports=payoutReportResponse.getProject_report();
                        adapter.setNewList(reports);
                        if (reports.get(0).getMsg().equals("No Record Found")) {
                            binding.nodata.setVisibility(View.VISIBLE);
                            binding.recyclerview.setVisibility(View.GONE);
                        }
                        else{
                            binding.nodata.setVisibility(View.GONE);
                            binding.recyclerview.setVisibility(View.VISIBLE);
                        }
                        Toast.makeText(getContext(), payoutReportResponse.getProject_report().get(0).getMsg(), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("test1", e.getMessage());
                        Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), MainHelper.getUserDataList(sh).get(0).getUserId(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), MainHelper.getUserDataList(sh).get(0).getToken(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), MainHelper.getUserDataList(sh).get(0).getType().toLowerCase(), Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onFailure(Call<PayoutReportResponse> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }

    private void setRecyclerView() {
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        adapter =new ProjectPayoutAdapter(getContext(),reports);
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(adapter);


    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onWalletDetailEvent(WalletDetail event) {
        totalIncome = event.total;
        balance = event.balance;

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setTitle("Wallet("+String.valueOf(balance)+")");

        TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
        textView.setText(String.valueOf(balance));

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

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
//        getActivity().getMenuInflater().inflate(R.menu.home_other_option, menu);
        TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
        textView.setText(String.valueOf(balance));
    }

}