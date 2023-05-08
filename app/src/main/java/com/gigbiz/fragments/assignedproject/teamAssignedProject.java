package com.gigbiz.fragments.assignedproject;

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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.adapters.TeamAssignedProjectAdapter;
import com.gigbiz.databinding.FragmentTeamAssignedProjectBinding;
import com.gigbiz.fragments.mytask.MyTaskFragment;
import com.gigbiz.fragments.teamreport.Teamreportfragment;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AssignedModel;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.PayoutReportRequest;
import com.gigbiz.models.UserData;
import com.gigbiz.models.WalletDetail;
import com.gigbiz.models.details;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teamAssignedProject extends Fragment {
    FragmentTeamAssignedProjectBinding binding;
    TeamAssignedProjectAdapter adapter;
    public String totalIncome, balance, referral_url;
    Menu menu;
    SharedPreferences sh;
    List<UserData> userData;
    List<details> reports;//receiving model

    public teamAssignedProject() {
        // Required empty public constructor
    }


    public static teamAssignedProject newInstance(String param1, String param2) {
        teamAssignedProject fragment = new teamAssignedProject();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTeamAssignedProjectBinding.inflate(inflater, container, false);
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
                    getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home, new MyTaskFragment()).commit();
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
//        Call<AssignedModel> call = RetrofitClient.getInstance().getApi().getTeamReportResponse(
//                new PayoutReportRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
//                        MainHelper.getUserDataList(sh).get(0).getToken()
//                        ,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
//                ));
        Call<AssignedModel> call = RetrofitClient.getInstance().getApi().getTeamReportResponse(new PayoutReportRequest("MTg4Ng==", "9795f6499ca2c20924676e36e48bbc2a", "employee"));
        call.enqueue(new Callback<AssignedModel>() {
            @Override
            public void onResponse(Call<AssignedModel> call, Response<AssignedModel> response) {
                AssignedModel assignedModel = response.body();
                if (response.isSuccessful()) {
                    try {

                        assert assignedModel != null;
                        reports = assignedModel.getTeam_name();
                        if (reports!=null){
                            adapter.setNewList(reports);
                        }
                        if (reports.size() == 0) {
                            binding.nodata.setVisibility(View.VISIBLE);
                            binding.recyclerview.setVisibility(View.GONE);
                        } else {
                            binding.nodata.setVisibility(View.GONE);
                            binding.recyclerview.setVisibility(View.VISIBLE);
                        }

                        Toast.makeText(getContext(), assignedModel.getTeam_name().get(0).getMsg(), Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<AssignedModel> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), " Failure hora h", Toast.LENGTH_SHORT).show();

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
        adapter = new TeamAssignedProjectAdapter(getContext(), reports, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                Teamreportfragment teamreportfragment = new Teamreportfragment();
                teamreportfragment.newInstance(reports.get(position).getTeam_id().toString(),reports.get(position).getTeam_token().toString());
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frag_team_report, teamreportfragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(adapter);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onWalletDetailEvent(WalletDetail event) {
        totalIncome = event.total;
        balance = event.balance;

//        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
//        navigationView.getMenu().getItem(1).setTitle("Wallet(" + String.valueOf(balance) + ")");
//
//        TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
//        textView.setText(String.valueOf(balance));

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