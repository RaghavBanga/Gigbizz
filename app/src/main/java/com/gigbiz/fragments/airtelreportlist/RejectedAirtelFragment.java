package com.gigbiz.fragments.airtelreportlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.gigbiz.adapters.RejectedReportListAirtelAdapter;
import com.gigbiz.databinding.FragmentRejectedAirtelBinding;
import com.gigbiz.fragments.datadetailscreen.DataDetailAirtelFragment;
import com.gigbiz.helper.ItemOffsetDecoration;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AirtelReportListResponse;
import com.gigbiz.models.RejectedAirtel;
import com.gigbiz.models.UserTaskRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RejectedAirtelFragment extends Fragment {

    FragmentRejectedAirtelBinding binding;
    public String user_id;
    public String token;
    public String usertype;
    List<RejectedAirtel> projects1;
    SharedPreferences sh;
    RejectedReportListAirtelAdapter adapter;
    public boolean check = false;


    public static RejectedAirtelFragment newInstance(String param1, String param2) {
        RejectedAirtelFragment fragment = new RejectedAirtelFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRejectedAirtelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
        binding.progressbar.setVisibility(View.VISIBLE);


        projects1 = new ArrayList<>();

        binding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvList.addItemDecoration(itemDecoration);
        adapter = new RejectedReportListAirtelAdapter(projects1, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
                    DataDetailAirtelFragment dataDetailScreenFragment = new DataDetailAirtelFragment();
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

                Call<AirtelReportListResponse> call = RetrofitClient.getInstance().getApi().getAirtelReportListResponse(
                        new UserTaskRequest(userId, userToken, userType));

                call.enqueue(new Callback<AirtelReportListResponse>() {
                    @Override
                    public void onResponse(Call<AirtelReportListResponse> call, Response<AirtelReportListResponse> response) {

                        AirtelReportListResponse reportListResponse = response.body();
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
                    public void onFailure(Call<AirtelReportListResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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