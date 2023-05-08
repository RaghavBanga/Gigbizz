package com.gigbiz.fragments.reportlistinsurancecommon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.adapters.SubmittedReportListInsuranceCommonAdapter;
import com.gigbiz.databinding.FragmentSubmittedCreditCardCommonBinding;
import com.gigbiz.fragments.datadetailscreen.DataDetailInsuranceFragment;
import com.gigbiz.helper.ItemOffsetDecoration;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.InsuranceReportListResponse;
import com.gigbiz.models.SubmittedInsurance;
import com.gigbiz.models.UserTaskRequestReport;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubmittedInsuranceCommonFragment extends Fragment {

    FragmentSubmittedCreditCardCommonBinding binding;
    public String user_id;
    public String token;
    public String usertype;
    List<SubmittedInsurance> projects1;
    SharedPreferences sh;
    SubmittedReportListInsuranceCommonAdapter adapter;
    public boolean check = false;

    public SubmittedInsuranceCommonFragment() {
        // Required empty public constructor
    }


    public static SubmittedInsuranceCommonFragment newInstance(String param1, String param2) {
        SubmittedInsuranceCommonFragment fragment = new SubmittedInsuranceCommonFragment();
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
        binding = FragmentSubmittedCreditCardCommonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
        binding.progressbar.setVisibility(View.VISIBLE);


        projects1 = new ArrayList<>();

        binding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvList.addItemDecoration(itemDecoration);
        adapter = new SubmittedReportListInsuranceCommonAdapter(projects1, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
                    DataDetailInsuranceFragment dataDetailScreenFragment = new DataDetailInsuranceFragment();
                    dataDetailScreenFragment.newInstance(null, null, projects1, projects1.get(position).getProjectId(), position);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.container_other_frag, dataDetailScreenFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvList.setAdapter(adapter);
        binding.progressbar.setVisibility(View.GONE);


        getReportList();

        return root;
    }

    private void getReportList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();

                Call<InsuranceReportListResponse> call = RetrofitClient.getInstance().getApi().getInsuranceReportListResponse(
                        new UserTaskRequestReport(userId, userToken, userType));

                call.enqueue(new Callback<InsuranceReportListResponse>() {
                    @Override
                    public void onResponse(Call<InsuranceReportListResponse> call, Response<InsuranceReportListResponse> response) {

                        InsuranceReportListResponse reportListResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
                                if (reportListResponse.getSubmitted() != null) {
                                    projects1 = reportListResponse.getSubmitted();
                                }

                                check = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            } finally {

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.setNewList(projects1);
                                    }
                                });

                            }

                            if (check == true) {
//                                if(sharedPrefManger.isCodeExecuted()==true){
//                                    sharedPrefManger.setReportListSubmittedDetails(reportListResponse.getSubmitted());
//                                    sharedPrefManger.setReportListRejectedDetails(reportListResponse.getRejected());
//                                    sharedPrefManger.setReportListApprovedDetails(reportListResponse.getApproved());
//                                    sharedPrefManger.setCodeExecuted();
//                                }

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<InsuranceReportListResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("mintupwerrorSubmitted", t.getMessage());

                    }
                });
            }
        });

        thread.start();
    }


//    private void getUserData() {
//
//        for (int i = 0; i < userData.size(); i++) {
////            if (formDashBoardActivity.value.equals(userData.get(i).getProjectId())) {
//            //todo need to change here after api format change
//
//            user_id = userData.get(0).getUserId();
//            usertype = userData.get(0).getType();
//            token = userData.get(0).getToken();
//
////            }
//        }
//
//    }


    @Override
    public void onResume() {
        super.onResume();

    }
}