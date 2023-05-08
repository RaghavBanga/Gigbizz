package com.gigbiz.fragments.amazonform.shopinfoamazon;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentShopInfoAmazonBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.Project;
import com.gigbiz.models.SubmitAmazonFirstRequest;
import com.gigbiz.models.SubmitAmazonFirstResponse;
import com.gigbiz.models.UserData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInfoAmazonFragment extends Fragment {

    private ShopInfoAmazonViewModel mViewModel;
    FragmentShopInfoAmazonBinding binding;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    List<UserData> userData;
    private boolean check = false;
    public RadioButton gst_status_radioButton;
    List<Project> list;
    public String value, type_list;

    public static ShopInfoAmazonFragment newInstance() {
        return new ShopInfoAmazonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentShopInfoAmazonBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        userData = new ArrayList<>();
        list = new ArrayList<>();
        userData = MainHelper.getUserDataList(sh);

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainHelper.isValidEmail(binding.RegisterMailId.getText().toString().trim()) == false) {
                    Toast.makeText(getContext(), "please enter correct email id", Toast.LENGTH_SHORT).show();
                }else if (MainHelper.isValidPhoneNumber(binding.sellerMob.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct phone number", Toast.LENGTH_SHORT).show();
                }else if ((binding.sellerName.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter seller name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.sellerStoreName.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter seller store name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.City.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter city name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.GSTNumber.getText().toString().trim()).length()!=15){
                    Toast.makeText(getContext(), "please enter correct GST number ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.radioGroup1.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(getContext(), "please check one GST status checkbox ", Toast.LENGTH_SHORT).show();
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

    private void submitFirstReport(View root) {
        int selectedId1 = binding.radioGroup1.getCheckedRadioButtonId();
        gst_status_radioButton = root.findViewById(selectedId1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (gst_status_radioButton == null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }
                else {
                    Call<SubmitAmazonFirstResponse> call = RetrofitClient.getInstance().getApi().getAmazonFirstResponse(
                            new SubmitAmazonFirstRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                    MainHelper.getUserDataList(sh).get(0).getToken()
                                    ,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                    ,value,
                                    binding.sellerStoreName.getText().toString().trim(),
                                    binding.sellerName.getText().toString().trim(),
                                    binding.sellerMob.getText().toString().trim(),
                                    binding.City.getText().toString().trim(),
                                    binding.RegisterMailId.getText().toString().trim(),
                                    binding.GSTNumber.getText().toString().trim()
                                    ,gst_status_radioButton.getText().toString().trim()
                            ));

                    call.enqueue(new Callback<SubmitAmazonFirstResponse>() {
                        @Override
                        public void onResponse(Call<SubmitAmazonFirstResponse> call, Response<SubmitAmazonFirstResponse> response) {
                            SubmitAmazonFirstResponse submitFlipKartFirstResponse = response.body();
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
                                formDashBoardActivity.binding.viewPager.setCurrentItem(2, true);
                                EventBus.getDefault().post(new MessageEvent(submitFlipKartFirstResponse.getReportId()));


                            }


                        }

                        @Override
                        public void onFailure(Call<SubmitAmazonFirstResponse> call, Throwable t) {
                            try {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

            }
        });

        thread.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShopInfoAmazonViewModel.class);
        // TODO: Use the ViewModel
    }

}