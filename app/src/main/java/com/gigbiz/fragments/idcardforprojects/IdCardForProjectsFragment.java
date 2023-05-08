package com.gigbiz.fragments.idcardforprojects;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.adapters.IdCardSelectionAdapter;
import com.gigbiz.databinding.FragmentIdCardForProjectsBinding;
import com.gigbiz.fragments.viewidcard.ViewIdCardFragment;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.IcardUrl;
import com.gigbiz.models.IdCardResponseModel;
import com.gigbiz.models.UserTaskRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IdCardForProjectsFragment extends Fragment {

    private FragmentIdCardForProjectsBinding binding;
    List<IcardUrl> list;
    IdCardSelectionAdapter idCardSelectionAdapter;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public String idCard;


    public IdCardForProjectsFragment() {
        // Required empty public constructor
    }

    public IdCardForProjectsFragment newInstance(String param1) {
        IdCardForProjectsFragment fragment = new IdCardForProjectsFragment();
        this.idCard=param1;

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
        binding = FragmentIdCardForProjectsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(Utilities.APP_NAME, Context.MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getActivity(),sh);

        list = new ArrayList<IcardUrl>();

        getListOfProjectWithId();

        idCardSelectionAdapter = new IdCardSelectionAdapter(list, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
                    ViewIdCardFragment offerLetterFragment = new ViewIdCardFragment();
                    offerLetterFragment.newInstance(list,position);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.idCard_container, offerLetterFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        binding.rvList.setLayoutManager(mLayoutManager);
        binding.rvList.setItemAnimator(new DefaultItemAnimator());
        binding.rvList.setAdapter(idCardSelectionAdapter);

        return root;
    }

    public void getListOfProjectWithId(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<IdCardResponseModel> call = RetrofitClient.getInstance().getApi().getIdCardResponseModel(new
                        UserTaskRequest(userId, userToken, userType));

                call.enqueue(new Callback<IdCardResponseModel>() {
                    @Override
                    public void onResponse(Call<IdCardResponseModel> call, Response<IdCardResponseModel> response) {
                        IdCardResponseModel profileDetailResponse = response.body();
                        if (response.isSuccessful()) {

                            if (profileDetailResponse.getIcardUrl().get(0).getStatus()==1){
                                try {
                                    Toast.makeText(getContext(), profileDetailResponse.getIcardUrl().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                    list= profileDetailResponse.getIcardUrl();
                                    idCardSelectionAdapter.setNewList(list);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            binding.progressbar.setVisibility(View.GONE);
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            binding.progressbar.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            }else {
                                Toast.makeText(getContext(), profileDetailResponse.getIcardUrl().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.progressbar.setVisibility(View.GONE);
                                    }
                                });
                            }



                        }

//                        if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));


//                        }


                    }

                    @Override
                    public void onFailure(Call<IdCardResponseModel> call, Throwable t) {
//                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.progressbar.setVisibility(View.GONE);
                            }
                        });
                    }
                });
            }
        });

        thread.start();
    }

}