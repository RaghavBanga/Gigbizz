package com.gigbiz.fragments.livetraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.OnClickListnerliveTraining;
import com.gigbiz.RetrofitClient;
import com.gigbiz.adapters.LiveTraingListAdapter;
import com.gigbiz.databinding.FragmentLiveTrainingZoomBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.JoinedLiveTrainingRequest;
import com.gigbiz.models.JoinedLiveTrainingResponse;
import com.gigbiz.models.LiveTraining;
import com.gigbiz.models.LiveTrainingResponse;
import com.gigbiz.models.PwReportListRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LiveTrainingZoomFragment extends Fragment {

    private FragmentLiveTrainingZoomBinding binding;
    List<LiveTraining> liveTrainings;
    LiveTraingListAdapter liveTraingListAdapter;
    public boolean check1 = false;
    public boolean check2 = false;
    SharedPreferences sh;
    SharedPrefManger sharedPrefManger;
    String starttime,endtime;

    public LiveTrainingZoomFragment() {
        // Required empty public constructor
    }


    public static LiveTrainingZoomFragment newInstance(String param1, String param2) {
        LiveTrainingZoomFragment fragment = new LiveTrainingZoomFragment();
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
        binding = FragmentLiveTrainingZoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(SharedPrefManger.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getActivity(),sh);

        liveTrainings = new ArrayList<>();

        setUpLiveTrainingRec();
        getLiveTrainingList();

        return root;
    }

    private void setUpLiveTrainingRec() {
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        liveTraingListAdapter = new LiveTraingListAdapter(liveTrainings, new OnClickListnerliveTraining() {
            @Override
            public void onTaskItemViewClick(int position) {
                registeredForMeeting(position);
            }

            @Override
            public void onTaskItemsClick(int position) {
                String currentString = liveTrainings.get(position).getMeetingTime();
                String[] separated = currentString.split("-");
                starttime  =  separated[0];
                String originalTime = separated[1];
                String resultTime = originalTime.replaceAll("\\s(AM|PM)", "");
                endtime  =  resultTime;
                if (System.currentTimeMillis()>=MainHelper.getmsTime(starttime) && System.currentTimeMillis()<=MainHelper.getmsTime(endtime)) {
                    Uri uri = Uri.parse(liveTrainings.get(position).getMeetingUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "Currently live training is not started", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.rvList.setAdapter(liveTraingListAdapter);
    }

    private void registeredForMeeting(int position) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                Call<JoinedLiveTrainingResponse> call = RetrofitClient.getInstance().getApi().getJoinedLiveTrainingResponse(
                        new JoinedLiveTrainingRequest(userId,userToken,
                                MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                        ,MainHelper.getUserDataList(sh).get(0).getName()
                        ,String.valueOf(MainHelper.getUserDataList(sh).get(0).getEmail())
                        ,liveTrainings.get(position).getId()));

                call.enqueue(new Callback<JoinedLiveTrainingResponse>() {
                    @Override
                    public void onResponse(Call<JoinedLiveTrainingResponse> call, Response<JoinedLiveTrainingResponse> response) {

                        JoinedLiveTrainingResponse liveTrainingResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
                                Toast.makeText(getContext(), liveTrainingResponse.getMsg(), Toast.LENGTH_SHORT).show();

//                                if (liveTrainingResponse.getLiveTraining()!=null){
//                                    liveTrainings  = liveTrainingResponse.getLiveTraining();
//                                }
//                                binding.progressbar.setVisibility(View.GONE);
                                getLiveTrainingList();
                                check2 = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                check2 = false;
//                                binding.progressbar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }

                            if (check2 == true) {

                                if (getActivity()!=null){
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
//                                            binding.progressbar.setVisibility(View.GONE);
//                                            liveTraingListAdapter.setNewList(liveTrainings);
                                        }
                                    });
                                }
                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<JoinedLiveTrainingResponse> call, Throwable t) {
//                        binding.progressbar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        thread.start();

    }

    private void getLiveTrainingList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                Call<LiveTrainingResponse> call = RetrofitClient.getInstance().getApi().getLiveTrainingResponse(
                        new PwReportListRequest(userId,userToken,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()));

                call.enqueue(new Callback<LiveTrainingResponse>() {
                    @Override
                    public void onResponse(Call<LiveTrainingResponse> call, Response<LiveTrainingResponse> response) {

                        LiveTrainingResponse liveTrainingResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getContext(), liveTrainingResponse.getLiveTraining().get(0).getTitle(), Toast.LENGTH_SHORT).show();

                                if (liveTrainingResponse.getLiveTraining()!=null){
                                    liveTrainings  = liveTrainingResponse.getLiveTraining();
                                }
                                binding.progressbar.setVisibility(View.GONE);

                                check1 = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                check1 = false;
                                binding.progressbar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }

                            if (check1 == true) {

                                if (getActivity()!=null){
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            binding.progressbar.setVisibility(View.GONE);
                                            liveTraingListAdapter.setNewList(liveTrainings);
                                        }
                                    });
                                }
                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<LiveTrainingResponse> call, Throwable t) {
                        binding.progressbar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        thread.start();
    }
}