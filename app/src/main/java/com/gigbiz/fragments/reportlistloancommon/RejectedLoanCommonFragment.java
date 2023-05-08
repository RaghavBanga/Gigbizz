package com.gigbiz.fragments.reportlistloancommon;

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
import com.gigbiz.adapters.RejectedReportListLoanCommonAdapter;
import com.gigbiz.databinding.FragmentRejectedLoanCommonBinding;
import com.gigbiz.fragments.datadetailscreen.DataDetailLoanFragment;
import com.gigbiz.helper.ItemOffsetDecoration;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.LoanReportListResponse;
import com.gigbiz.models.RejectedLoan;
import com.gigbiz.models.UserTaskRequestReport;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RejectedLoanCommonFragment extends Fragment {

    FragmentRejectedLoanCommonBinding binding;
    public String user_id;
    public String token;
    public String usertype;
    List<RejectedLoan> projects1;
    SharedPreferences sh;
    RejectedReportListLoanCommonAdapter adapter;
    public boolean check = false;

    public RejectedLoanCommonFragment() {
        // Required empty public constructor
    }


    public static RejectedLoanCommonFragment newInstance(String param1, String param2) {
        RejectedLoanCommonFragment fragment = new RejectedLoanCommonFragment();

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
        binding = FragmentRejectedLoanCommonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
        binding.progressbar.setVisibility(View.VISIBLE);


        projects1 = new ArrayList<>();

        binding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvList.addItemDecoration(itemDecoration);
        adapter = new RejectedReportListLoanCommonAdapter(projects1, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
                    DataDetailLoanFragment dataDetailScreenFragment = new DataDetailLoanFragment();
                    dataDetailScreenFragment.newInstance(null, projects1, null, projects1.get(position).getProjectId(), position);
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

                Call<LoanReportListResponse> call = RetrofitClient.getInstance().getApi().getLoanReportListResponse(
                        new UserTaskRequestReport(userId, userToken, userType));

                call.enqueue(new Callback<LoanReportListResponse>() {
                    @Override
                    public void onResponse(Call<LoanReportListResponse> call, Response<LoanReportListResponse> response) {

                        LoanReportListResponse reportListResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
                                if (reportListResponse.getRejected() != null) {
                                    projects1 = reportListResponse.getRejected();
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
                    public void onFailure(Call<LoanReportListResponse> call, Throwable t) {
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