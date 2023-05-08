package com.gigbiz.fragments.reportlistselection;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.adapters.ReportListSelectionAdapter;
import com.gigbiz.databinding.FragmentReportListSelectionBinding;
import com.gigbiz.fragments.mytask.MyTaskFragment;
import com.gigbiz.fragments.reportlist.ReportListFragment;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.ReportList;
import com.gigbiz.models.ReportListSelectionResponse;
import com.gigbiz.models.TransactionHistoryRequest;
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

public class ReportListSelectionFragment extends Fragment {

    private ReportListSelectionViewModel mViewModel;
    FragmentReportListSelectionBinding binding;
    public RadioButton radioButton;
    List<ReportList> list;
    ArrayList<ReportList> filterdNames;
    ReportListSelectionAdapter reportListSelectionAdapter;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;
    public String totalIncome, balance, referral_url;
    Menu menu;


    public static ReportListSelectionFragment newInstance() {
        return new ReportListSelectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentReportListSelectionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(Utilities.APP_NAME, Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getActivity(),sh);

        list = new ArrayList<ReportList>();
        filterdNames = new ArrayList<>();

        setHasOptionsMenu(true);


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

        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

//        binding.next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int selectedId1 = binding.typeRadioGroup.getCheckedRadioButtonId();
//                radioButton = root.findViewById(selectedId1);
////                EventBus.getDefault().post(new FormTypeSend(radioButton.getText().toString().toLowerCase()));
//                try {
//                    ReportListFragment reportListFragment = new ReportListFragment();
//                    reportListFragment.newInstance(radioButton.getText().toString());
//                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.main_container, reportListFragment);
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                    ft.addToBackStack(null);
//                    ft.commit();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        getListOfProject();

        reportListSelectionAdapter = new ReportListSelectionAdapter(list, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
                    ReportListFragment reportListFragment = new ReportListFragment();
                    reportListFragment.newInstance(filterdNames.size() > 0?filterdNames.get(position).getHandler():list.get(position).getHandler());
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.main_container, reportListFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        binding.reportRec.setLayoutManager(new GridLayoutManager(getActivity(),2));
//        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing1);
//        binding.reportRec.addItemDecoration(itemDecoration);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.reportRec.setLayoutManager(mLayoutManager);
        binding.reportRec.setItemAnimator(new DefaultItemAnimator());
        binding.reportRec.setAdapter(reportListSelectionAdapter);


        return root;
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

    public void getListOfProject(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<ReportListSelectionResponse> call = RetrofitClient.getInstance()
                        .getApi().getReportListRequest(
                                new TransactionHistoryRequest(
                                        MainHelper.getUserDataList(sh).get(0).getUserId(),
                                        MainHelper.getUserDataList(sh).get(0).getToken()
                                ));

                call.enqueue(new Callback<ReportListSelectionResponse>() {
                    @Override
                    public void onResponse(Call<ReportListSelectionResponse> call, Response<ReportListSelectionResponse> response) {
                        ReportListSelectionResponse secondResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
                                check=true;
//                                Toast.makeText(getContext(), secondResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                if (secondResponse.getReportList()!=null){
                                    list = secondResponse.getReportList();
                                }
                                binding.progressbar.setVisibility(View.GONE);
//                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                check=false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            }

                        }

                        if (check==true){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    reportListSelectionAdapter.setNewList(secondResponse.getReportList());
                                    binding.progressbar.setVisibility(View.GONE);
                                }
                            });
                        }


                    }

                    @Override
                    public void onFailure(Call<ReportListSelectionResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        thread.start();
    }

    private void filter(String text) {
        filterdNames.clear();
        for (ReportList s : list) {
            if (s.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(s);
            }
        }

        reportListSelectionAdapter.filterList(filterdNames);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReportListSelectionViewModel.class);
        // TODO: Use the ViewModel
    }

}