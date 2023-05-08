package com.gigbiz.fragments.flipkartform.shopinfoflipkart;

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
import com.gigbiz.databinding.FragmentShopInfoFlipkartBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.Project;
import com.gigbiz.models.SubmitFlipKartFirstRequest;
import com.gigbiz.models.SubmitFlipKartFirstResponse;
import com.gigbiz.models.UserData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInfoFlipkartFragment extends Fragment {

    private ShopInfoFlipkartViewModel mViewModel;
    FragmentShopInfoFlipkartBinding binding;
    String[] category = {"AUTOMOBILE", "CLOTHING",
            "FOOTWEAR", "MOBILE", "ELECTRONICS", "GROCERY"
            , "HEALTH & MEDICAL", "HARDWARE", "SPORTS", "BEAUTY & PERSONAL",
            "STATIONARY", "HOME & KITCHEN", "Other"};

    String category_text;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    List<UserData> userData;
    List<Project> list;
    public String value, type_list;


    private boolean check = false;


    public static ShopInfoFlipkartFragment newInstance() {
        return new ShopInfoFlipkartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentShopInfoFlipkartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        userData = new ArrayList<>();
        list = new ArrayList<>();
        userData = MainHelper.getUserDataList(sh);

        //spinner for category
        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.category.setAdapter(ad);
        binding.category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category_text = category[i];
                if (category_text.equals("Other")) {
                    binding.other.setVisibility(View.VISIBLE);
                } else {
                    binding.other.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MainHelper.isValidEmail(binding.supplierEmail.getText().toString().trim()) == false) {
                    Toast.makeText(getContext(), "please enter correct email id", Toast.LENGTH_SHORT).show();
                }else if (MainHelper.isValidPhoneNumber(binding.supplierMobile.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct phone number", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.supplierGst.getText().toString().trim()).length()!=15){
                    Toast.makeText(getContext(), "please enter correct Gst number", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.supplierCity.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter your city name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.shopName.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter your Shop name ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.supplierPin.getText().toString().trim()).length()!=6){
                    Toast.makeText(getContext(), "please enter correct Pincode ", Toast.LENGTH_SHORT).show();
                }
                else if ((binding.pw.getText().toString().trim()).isEmpty()){
                    Toast.makeText(getContext(), "please enter a password ", Toast.LENGTH_SHORT).show();
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

//        for (int i = 0; i < userData.size(); i++) {
//            if (formDashBoardActivity.value.equals(userData.get(i).getProjectId())) {
//                formDashBoardActivity.project_id = userData.get(i).getProjectId();
//                formDashBoardActivity.user_id = userData.get(i).getUserId();
//                formDashBoardActivity.usertype = userData.get(i).getType();
//                formDashBoardActivity.token = userData.get(i).getToken();
//            }
//        }

    }

    private void submitFirstReport(View root) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<SubmitFlipKartFirstResponse> call = RetrofitClient.getInstance().getApi().getFlipKartFirstResponse(
                        new SubmitFlipKartFirstRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                MainHelper.getUserDataList(sh).get(0).getToken()
                                ,value,
                                category_text.equals("Other") ? binding.other.getText().toString().trim() : category_text,
                                binding.shopName.getText().toString().trim(),
                                binding.supplierEmail.getText().toString().trim(),
                                binding.pw.getText().toString().trim(),
                                binding.supplierMobile.getText().toString().trim(),
                                binding.supplierCity.getText().toString().trim(),
                                binding.supplierGst.getText().toString().trim(),
                                binding.supplierPin.getText().toString().trim(),
                                MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                        ));

                call.enqueue(new Callback<SubmitFlipKartFirstResponse>() {
                    @Override
                    public void onResponse(Call<SubmitFlipKartFirstResponse> call, Response<SubmitFlipKartFirstResponse> response) {
                        SubmitFlipKartFirstResponse submitFlipKartFirstResponse = response.body();
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
                    public void onFailure(Call<SubmitFlipKartFirstResponse> call, Throwable t) {
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
        mViewModel = new ViewModelProvider(this).get(ShopInfoFlipkartViewModel.class);
        // TODO: Use the ViewModel
    }

}