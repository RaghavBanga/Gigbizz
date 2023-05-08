package com.gigbiz.fragments.approved;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.adapters.ApprovedListAdapter;
import com.gigbiz.databinding.FragmentApprovedBinding;
import com.gigbiz.fragments.datadetailscreen.DataDetailScreenFragment;
import com.gigbiz.helper.ItemOffsetDecoration;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.ApprovedGood;
import com.gigbiz.models.GoodWorkerReportListResponse;
import com.gigbiz.models.UserTaskRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedFragment extends Fragment {

    private ApprovedViewModel mViewModel;
    FragmentApprovedBinding binding;
    public String user_id;
    public String token;
    public String usertype;
//    List<Approved> userData;
    List<ApprovedGood> projects1;
    SharedPreferences sh;
    ApprovedListAdapter adapter;
    public boolean check = false;
    SharedPrefManger sharedPrefManger;




    public static ApprovedFragment newInstance() {
        return new ApprovedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentApprovedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(SharedPrefManger.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        binding.progressbar.setVisibility(View.VISIBLE);


//        userData = new ArrayList<>();
        projects1 = new ArrayList<>();
//        if (MainHelper.getReportListApprovedDetails(sh)!=null){
//            userData = MainHelper.getReportListApprovedDetails(sh);
//        }
        try {
            sharedPrefManger = new SharedPrefManger(getActivity(),sh);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            binding.rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvList.addItemDecoration(itemDecoration);
//        adapter = new ApprovedListAdapter(userData, new OnClickListner() {
//            @Override
//            public void onTaskItemClick(int position) {
//                try {
////                    Toast.makeText(getContext(), userData.get(position).getCatName(), Toast.LENGTH_SHORT).show();
//                    DataDetailScreenFragment dataDetailScreenFragment = new DataDetailScreenFragment();
//                    dataDetailScreenFragment.newInstance(userData,null,null,userData.get(position).getShopNo(),position);
//                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.container_other_frag, dataDetailScreenFragment);
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);// it will anim while calling fragment.
//                    ft.addToBackStack(null); // it will manage back stack of fragments.
//                    ft.commit();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
        adapter = new ApprovedListAdapter(projects1, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    Toast.makeText(getContext(), userData.get(position).getCatName(), Toast.LENGTH_SHORT).show();
                    DataDetailScreenFragment dataDetailScreenFragment = new DataDetailScreenFragment();
                    dataDetailScreenFragment.newInstance(projects1,null,null,projects1.get(position).getProjectId(),position);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container_other_frag, dataDetailScreenFragment);
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


//        getUserData();
        getReportList();

        return root;
    }

    private void getReportList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId  = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken  = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType  = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
//                Call<ReportListResponse> call = RetrofitClient.getInstance().getApi().getReportListResponse(
//                        new UserTaskRequest(userId,userToken,userType));
//
//                call.enqueue(new Callback<ReportListResponse>() {
//                    @Override
//                    public void onResponse(Call<ReportListResponse> call, Response<ReportListResponse> response) {
//
//                        ReportListResponse reportListResponse = response.body();
//                        if (response.isSuccessful()) {
//
//                            try {
////                                if (reportListResponse.getApproved()!=null) {
////                                    userData = reportListResponse.getApproved();
////                                }
//
//                                check=true;
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                check=false;
//
//                            }finally {
//
//                                try {
//                                    getActivity().runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
////                                            adapter.setNewList(userData);
//                                        }
//                                    });
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//
//                            if (check==true){
////                                if(sharedPrefManger.isCodeExecuted()==true){
////                                    sharedPrefManger.setReportListSubmittedDetails(reportListResponse.getSubmitted());
////                                    sharedPrefManger.setReportListRejectedDetails(reportListResponse.getRejected());
////                                    sharedPrefManger.setReportListApprovedDetails(reportListResponse.getApproved());
////                                    sharedPrefManger.setCodeExecuted();
////                                }
//
//                            }
//
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ReportListResponse> call, Throwable t) {
//                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

                Call<GoodWorkerReportListResponse> call = RetrofitClient.getInstance().getApi().getGoodReportListResponse(
                        new UserTaskRequest(userId,userToken,userType));

                call.enqueue(new Callback<GoodWorkerReportListResponse>() {
                    @Override
                    public void onResponse(Call<GoodWorkerReportListResponse> call, Response<GoodWorkerReportListResponse> response) {

                        GoodWorkerReportListResponse reportListResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
                                if (reportListResponse.getApproved()!=null) {
                                    projects1 = reportListResponse.getApproved();
                                }

                                check=true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                check=false;

                            }finally {

                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
//                                            adapter.setNewList(userData);
                                            adapter.setNewList(projects1);
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (check==true){
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
                    public void onFailure(Call<GoodWorkerReportListResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        thread.start();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (MainHelper.getReportListApprovedDetails(sh)!=null){
//            userData = MainHelper.getReportListApprovedDetails(sh);
//        }
//
//        adapter.setNewList(userData);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApprovedViewModel.class);
        // TODO: Use the ViewModel
    }

}