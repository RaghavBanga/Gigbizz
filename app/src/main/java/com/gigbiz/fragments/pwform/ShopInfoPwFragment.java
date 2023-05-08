package com.gigbiz.fragments.pwform;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentShopInfoPwBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.PwFirstFormRequest;
import com.gigbiz.models.PwFirstFormResponse;
import com.gigbiz.models.UserData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopInfoPwFragment extends Fragment {

    FragmentShopInfoPwBinding binding;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    List<UserData> userData;
    public boolean check = false;
    public RadioButton radioButtonQualification , radioButtonGender;
    public String projectId;


    public ShopInfoPwFragment() {
        // Required empty public constructor
    }

    public static ShopInfoPwFragment newInstance(String param1, String param2) {
        ShopInfoPwFragment fragment = new ShopInfoPwFragment();

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
        binding = FragmentShopInfoPwBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        userData = new ArrayList<>();
        userData = MainHelper.getUserDataList(sh);

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainHelper.isValidEmail(binding.email.getText().toString().trim()) == false) {
                    Toast.makeText(getContext(), "please enter correct seller email id", Toast.LENGTH_SHORT).show();
                }else if (MainHelper.isValidEmail(binding.bookSellerEmail.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct bussiness email id", Toast.LENGTH_SHORT).show();
                }else if (MainHelper.isValidPhoneNumber(binding.phoneNumber.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct phone number", Toast.LENGTH_SHORT).show();
                }else if (MainHelper.isValidPhoneNumber(binding.alterPhoneNumber.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct alternate phone number", Toast.LENGTH_SHORT).show();
                }else if (binding.pwcoupon.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter Pw Coupon code", Toast.LENGTH_SHORT).show();
                }else if (binding.name.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter Shopkeeper name", Toast.LENGTH_SHORT).show();
                }else if (binding.storeAddress.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter Shop Address", Toast.LENGTH_SHORT).show();
                }else if (binding.bookStoreName.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter Store Name", Toast.LENGTH_SHORT).show();
                }else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFirstReport(root);
                }

            }
        });

        getUserData();

        return root;

    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        projectId = event.message;
//        listtype = event.list_type;
//        Toast.makeText(getActivity(), event.message+"Earning", Toast.LENGTH_SHORT).show();
//
//        if (listtype.equals(Utilities.USER_TASK_DETAIL_FIELDS)) {
//            list = MainHelper.getUserTaskDetails(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST)) {
//            list = MainHelper.getUserTaskDetailsCREDIT_CARD(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsHOME_LOANS(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh);
//            userValidate();
//        }  else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST)) {
//            list = MainHelper.getUserTaskDetailsCAR_LOANS(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HEALTH_LIST)) {
//            list = MainHelper.getUserTaskDetailsHEALTH(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LIST)) {
//            list = MainHelper.getUserTaskDetailsCAR(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_LIFE_LIST)) {
//            list = MainHelper.getUserTaskDetailsLIFE(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_SAVING_LIST)) {
//            list = MainHelper.getUserTaskDetailsSAVING(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_DEMAT_LIST)) {
//            list = MainHelper.getUserTaskDetailsDEMAT(sh);
//            userValidate();
//        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_MORE_LIST)) {
//            list = MainHelper.getUserTaskDetailsMORE(sh);
//            userValidate();
//        }


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
        int selectedId1 = binding.genderradio.getCheckedRadioButtonId();
        radioButtonGender = root.findViewById(selectedId1);
        int selectedId2 = binding.qualificationradio.getCheckedRadioButtonId();
        radioButtonQualification = root.findViewById(selectedId2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (radioButtonGender == null||radioButtonQualification == null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        }
                    });
                }
                else {
                    Call<PwFirstFormResponse> call = RetrofitClient.getInstance().getApi().getPwFirstFormResponse(
                            new PwFirstFormRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                    MainHelper.getUserDataList(sh).get(0).getToken(),
                                    MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                    ,projectId,
                                    binding.name.getText().toString(),
                                    binding.email.getText().toString().trim(),
                                    binding.age.getText().toString().trim(),
                                    radioButtonGender.getText().toString()=="Other"?binding.otherGenderEdit.getText().toString().trim():radioButtonGender.getText().toString(),
                                    binding.bookSellerEmail.getText().toString().trim(),
                                    binding.phoneNumber.getText().toString().trim(),
                                    binding.alterPhoneNumber.getText().toString().trim(),
                                    radioButtonQualification.getText().toString()=="Other"?binding.otherqualificationEdit.getText().toString().trim():radioButtonQualification.getText().toString(),
                                    binding.state.getText().toString().trim(),
                                    binding.City.getText().toString().trim(),
                                    binding.bookStoreName.getText().toString().trim(),
                                    binding.storeAddress.getText().toString().trim(),
                                    binding.pinCode.getText().toString().trim(),
                                    binding.shopLocationLink.getText().toString().trim(),
                                    binding.schoolNo.getText().toString().trim(),
                                    binding.schoolName.getText().toString().trim(),
                                    binding.noOfStaff.getText().toString().trim(),
                                    binding.monthlyIncome.getText().toString().trim(),
                                    binding.pwcoupon.getText().toString().trim()
                            ));

                    call.enqueue(new Callback<PwFirstFormResponse>() {
                        @Override
                        public void onResponse(Call<PwFirstFormResponse> call, Response<PwFirstFormResponse> response) {
                            PwFirstFormResponse submitFlipKartFirstResponse = response.body();
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
                        public void onFailure(Call<PwFirstFormResponse> call, Throwable t) {
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
//        mViewModel = new ViewModelProvider(this).get(ShopInfoAirtelViewModel.class);
    }

}