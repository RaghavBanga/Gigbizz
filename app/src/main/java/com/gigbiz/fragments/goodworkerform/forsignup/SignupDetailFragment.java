package com.gigbiz.fragments.goodworkerform.forsignup;

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

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.SendDataToFragment;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentSignupDetailBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.GoodWorkerFirstRequest;
import com.gigbiz.models.GoodWorkerFirstResponse;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.MessageForReportId;
import com.gigbiz.models.UserData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupDetailFragment extends Fragment {

    private SignupDetailViewModel mViewModel;
    FragmentSignupDetailBinding binding;
    private RadioButton radioButton;
    List<UserData> userData;
    private FormDashBoardActivity formDashBoardActivity;
    SharedPreferences sh;
    private boolean check = false;
    SharedPrefManger sharedPrefManger;
    private SendDataToFragment sendDataToFragment;
    public String value, type_list;

    public SignupDetailFragment() {
    }
    public SignupDetailFragment(SendDataToFragment sendDataToFragment) {
        this.sendDataToFragment = sendDataToFragment;
    }

//    public static SignupDetailFragment newInstance() {
//        return new SignupDetailFragment();
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(),getActivity().getSharedPreferences("gigbiz",MODE_PRIVATE));

//        list = new ArrayList<>();
        userData = new ArrayList<>();
//        list = MainHelper.getUserTaskDetails(sh);
        userData = MainHelper.getUserDataList(sh);


        addListenerOnButton(root);
        getUserData();

        return root;
    }

    private void addListenerOnButton(View root) {


        binding.next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = binding.radioGroup.getCheckedRadioButtonId();
                radioButton = root.findViewById(selectedId);

                formDashBoardActivity.gw_type = radioButton.getText().toString().toLowerCase().trim();

//                sharedPrefManger.setGoodWorkerType(radioButton.getText().toString().toLowerCase().trim());
                EventBus.getDefault().post(new MessageEvent(radioButton.getText().toString().toLowerCase().trim()));
                binding.progressbar.setVisibility(View.VISIBLE);
                sumbitSignupReport();

            }

        });

    }

    private void getUserData() {

        for (int i = 0; i < userData.size(); i++) {
//            if (formDashBoardActivity.value.equals(userData.get(i).getProjectId())) {
//                formDashBoardActivity.project_id = userData.get(0).getProjectId();
                formDashBoardActivity.user_id = userData.get(0).getUserId();
                formDashBoardActivity.usertype = userData.get(0).getType();
                formDashBoardActivity.token = userData.get(0).getToken();
//            }
        }
//
//        for (int i = 0; i < list.size(); i++) {
//            if (formDashBoardActivity.value.equals(list.get(i).getProjectId())) {
//                project_id = list.get(i).getre();
//                user_id = list.get(i).getUserId();
//                usertype = list.get(i).getType();
//                token = list.get(i).getToken();
//            }
//        }

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


    private void sumbitSignupReport() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Call<GoodWorkerFirstResponse> call = RetrofitClient.getInstance()
                        .getApi().sendFirstFormGoodWorker(new GoodWorkerFirstRequest(
                                MainHelper.getUserDataList(sh).get(0).getUserId(),
                                MainHelper.getUserDataList(sh).get(0).getToken(),
                                value,
                                radioButton.getText().toString().toLowerCase(),
                                MainHelper.getUserDataList(sh).get(0).getType().toString().toLowerCase()));

                call.enqueue(new Callback<GoodWorkerFirstResponse>() {
                    @Override
                    public void onResponse(Call<GoodWorkerFirstResponse> call, Response<GoodWorkerFirstResponse> response) {
                        GoodWorkerFirstResponse firstResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
//                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
                                formDashBoardActivity.report_id=firstResponse.getReportId();
                                Toast.makeText(getContext(), firstResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);
                                check=true;
//                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                check=false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            }

                        }

                        if (check==true){
                            formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
                            formDashBoardActivity.binding.viewPager.setCurrentItem(1, true);
                            EventBus.getDefault().post(new MessageForReportId(firstResponse.getReportId()));
                        }


                    }

                    @Override
                    public void onFailure(Call<GoodWorkerFirstResponse> call, Throwable t) {
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
        mViewModel = new ViewModelProvider(this).get(SignupDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}