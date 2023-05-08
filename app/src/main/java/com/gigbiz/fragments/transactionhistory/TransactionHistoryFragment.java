package com.gigbiz.fragments.transactionhistory;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.adapters.TransactionHIstoryAdapter;
import com.gigbiz.databinding.FragmentTransactionHistoryBinding;
import com.gigbiz.helper.ItemOffsetDecoration;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.Redeem;
import com.gigbiz.models.TransactionHistoryRequest;
import com.gigbiz.models.TransactionHistoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TransactionHistoryFragment extends Fragment {

    private FragmentTransactionHistoryBinding binding;
    List<Redeem> redeemList;
    SharedPrefManger sharedPrefManger;
    TransactionHIstoryAdapter adapter;
    SharedPreferences sh;
    private boolean check = false;


    public TransactionHistoryFragment() {
        // Required empty public constructor
    }

    public static TransactionHistoryFragment newInstance(String param1, String param2) {
        TransactionHistoryFragment fragment = new TransactionHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTransactionHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(Utilities.APP_NAME, Context.MODE_PRIVATE);
        binding.progressbar.setVisibility(View.VISIBLE);


//        list = new ArrayList<>();
        redeemList = new ArrayList<>();
        sharedPrefManger = new SharedPrefManger(getActivity(),sh);

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().beginTransaction()
                            .remove(TransactionHistoryFragment.this).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        try {
            binding.rvList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvList.addItemDecoration(itemDecoration);
        adapter = new TransactionHIstoryAdapter(redeemList, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {

            }
        });
        binding.rvList.setAdapter(adapter);


        getUserTransactionHistory();

        return root;
    }

    private void getUserTransactionHistory() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId  = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken  = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType  = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<TransactionHistoryResponse> call = RetrofitClient.getInstance().getApi().getTransactionHistoryRequest(
                        new TransactionHistoryRequest(userId,userToken));

                call.enqueue(new Callback<TransactionHistoryResponse>() {
                    @Override
                    public void onResponse(Call<TransactionHistoryResponse> call, Response<TransactionHistoryResponse> response) {

                        TransactionHistoryResponse transactionHistoryResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getContext(), transactionHistoryResponse.getRedeemList().get(0).getMsg(), Toast.LENGTH_SHORT).show();
//                                for (Redeem redeem:transactionHistoryResponse.getRedeemList()) {
//                                    redeemList.add(new Redeem(redeem.))
//                                }
                                redeemList = transactionHistoryResponse.getRedeemList();
                                binding.progressbar.setVisibility(View.GONE);

                                check=true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                check=false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);


                            }finally {

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            if (redeemList!=null){
                                                adapter.setNewList(redeemList);
                                                binding.progressbar.setVisibility(View.GONE);
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });

                            }

                            if (check==true){
//                                sharedPrefManger.setListUserTaskDetails(transactionHistoryResponse.getProjects());
                                binding.progressbar.setVisibility(View.GONE);

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<TransactionHistoryResponse> call, Throwable t) {
                        binding.progressbar.setVisibility(View.GONE);

                    }
                });
            }
        });

        thread.start();
    }
}