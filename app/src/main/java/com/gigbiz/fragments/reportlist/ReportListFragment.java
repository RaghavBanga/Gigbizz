package com.gigbiz.fragments.reportlist;

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
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.adapters.TabLayoutReportListAdapter;
import com.gigbiz.databinding.FragmentReportListBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.ReportListResponse;
import com.gigbiz.models.UserTaskRequest;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportListFragment extends Fragment {

    FragmentReportListBinding binding;
    private ReportListViewModel mViewModel;
    public String user_id;
    public String token;
    public String usertype;
    public String form_type;
    SharedPreferences sh;
    SharedPrefManger sharedPrefManger;
    public boolean check = false;

    public ReportListFragment newInstance(String s) {
        this.form_type=s;
        return new ReportListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentReportListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getActivity(),sh);

//        String value = sharedPrefManger.isCodeExecuted();
//        if(!sharedPrefManger.isCodeExecuted().equals("1")) {
//            getReportList();
//        }
        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().beginTransaction()
                            .remove(ReportListFragment.this).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("SUBMITTED"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("APPROVED"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("REJECTED"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_START);

        final TabLayoutReportListAdapter adapter = new TabLayoutReportListAdapter(getActivity(),getChildFragmentManager(), binding.tabLayout.getTabCount(),form_type);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //after removing this line tabs will not work
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return root;
    }

    private void getReportList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId  = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken  = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType  = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<ReportListResponse> call = RetrofitClient.getInstance().getApi().getReportListResponse(
                        new UserTaskRequest(userId,userToken,userType));

                call.enqueue(new Callback<ReportListResponse>() {
                    @Override
                    public void onResponse(Call<ReportListResponse> call, Response<ReportListResponse> response) {

                        ReportListResponse reportListResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getContext(), reportListResponse.getSubmitted().get(0).getCatName(), Toast.LENGTH_SHORT).show();
//                                projects = reportListResponse.getSubmitted();
//                                for (Project project:reportListResponse.getProjects()) {
//
//                                    projects.add(new Submitted(project.getProjectId(),project.getProjectTitle(), project.getCompName(),
//                                            project.getLogo(),project.getTrainingType(),project.getProjectStatus()
//                                            ,"Rs "+project.getPriceStart()+"-"+" Rs "+project.getPriceEnd()));
//                                }
                                check=true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                check=false;
//                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }finally {

//                                getActivity().runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        adapter.setNewList(projects);
//                                    }
//                                });

                            }

                            if (check==true){

                                if (reportListResponse!=null){
                                    if(reportListResponse.getApproved()!=null
                                            ||reportListResponse.getSubmitted()!=null
                                            ||reportListResponse.getRejected()!=null){
                                        sharedPrefManger.setReportListSubmittedDetails(reportListResponse.getSubmitted());
                                        sharedPrefManger.setReportListRejectedDetails(reportListResponse.getRejected());
                                        sharedPrefManger.setReportListApprovedDetails(reportListResponse.getApproved());
                                        sharedPrefManger.setCodeExecuted();
                                    }

                                }

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<ReportListResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        thread.start();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReportListViewModel.class);
        // TODO: Use the ViewModel
    }

}