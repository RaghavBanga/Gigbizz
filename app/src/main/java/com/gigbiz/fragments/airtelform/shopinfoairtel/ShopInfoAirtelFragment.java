package com.gigbiz.fragments.airtelform.shopinfoairtel;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentShopInfoAirtelBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.Project;
import com.gigbiz.models.SubmitAirtelReportFirstRequest;
import com.gigbiz.models.SubmitAirtelReportFirstResponse;
import com.gigbiz.models.UserData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInfoAirtelFragment extends Fragment {

    private ShopInfoAirtelViewModel mViewModel;
    FragmentShopInfoAirtelBinding binding;
    String[] agency = {"E-Boost Services", "Amitesh",
            "KVR", "Priyanshu Raj", "Workeget Talentmind", "Story Gropu Viplop"
            , "Vijay Pratap Singh", "Quick Mark"};

    String agency_text;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    List<UserData> userData;
    public boolean check = false;
    List<Project> list;
    public String value, type_list;

    public static ShopInfoAirtelFragment newInstance() {
        return new ShopInfoAirtelFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentShopInfoAirtelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        userData = new ArrayList<>();
        list = new ArrayList<>();
        userData = MainHelper.getUserDataList(sh);

        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, agency);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.agency.setAdapter(ad);
        binding.agency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                agency_text = agency[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainHelper.isValidPhoneNumber(binding.enterMerchantNo.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "Please Enter correct merchant no.", Toast.LENGTH_SHORT).show();
                }else if (binding.enterMerchantName.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please Enter merchant name ", Toast.LENGTH_SHORT).show();
                }
                else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFirstReport(root);
                }

            }
        });

        getUserData();

        return root;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        value = event.message;
        type_list = event.list_type;
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

    private void getUserData() {

        for (int i = 0; i < userData.size(); i++) {
            if (formDashBoardActivity.value.equals(userData.get(i).getProjectId())) {
                formDashBoardActivity.project_id = userData.get(i).getProjectId();
                formDashBoardActivity.user_id = userData.get(i).getUserId();
                formDashBoardActivity.usertype = userData.get(i).getType();
                formDashBoardActivity.token = userData.get(i).getToken();
            }
        }

    }

    private void submitFirstReport(View root) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<SubmitAirtelReportFirstResponse> call = RetrofitClient.getInstance().getApi().getAirtelReportFirstResponse(
                        new SubmitAirtelReportFirstRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                MainHelper.getUserDataList(sh).get(0).getToken(),
                                MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                ,value,
                                agency_text,
                                binding.enterMerchantName.getText().toString().trim(),
                                binding.enterMerchantNo.getText().toString().trim()
                        ));

                call.enqueue(new Callback<SubmitAirtelReportFirstResponse>() {
                    @Override
                    public void onResponse(Call<SubmitAirtelReportFirstResponse> call, Response<SubmitAirtelReportFirstResponse> response) {
                        SubmitAirtelReportFirstResponse submitFlipKartFirstResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
                                formDashBoardActivity.report_id = submitFlipKartFirstResponse.getReportId();
                                check = true;
                                Toast.makeText(getContext(), submitFlipKartFirstResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                binding.progressbar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (check == true) {

                            formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
                            formDashBoardActivity.binding.viewPager.setCurrentItem(1, true);
                            EventBus.getDefault().post(new MessageEvent(submitFlipKartFirstResponse.getReportId()));


                        }


                    }

                    @Override
                    public void onFailure(Call<SubmitAirtelReportFirstResponse> call, Throwable t) {
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShopInfoAirtelViewModel.class);
        // TODO: Use the ViewModel
    }

}