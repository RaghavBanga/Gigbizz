package com.gigbiz.fragments.eligibility;

import static com.gigbiz.DatabaseHandler.SharedPrefManger.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gigbiz.Utilities;
import com.gigbiz.activity.LandingPage;
import com.gigbiz.databinding.FragmentEligibilityTabBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.Project;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class EligibilityTabFragment extends Fragment {

    FragmentEligibilityTabBinding binding;
    List<Project> list;
    SharedPreferences sh;
    public String value,listtype;


    public EligibilityTabFragment() {
        // Required empty public constructor
    }


    public static EligibilityTabFragment newInstance(String param1, String param2) {
        EligibilityTabFragment fragment = new EligibilityTabFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEligibilityTabBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        list = new ArrayList<>();
//        list = MainHelper.getUserTaskDetails(sh);


        return root;
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        value = event.message;
        listtype = event.list_type;
//        Toast.makeText(getActivity(), event.message+"Earning", Toast.LENGTH_SHORT).show();

        if (listtype.equals(Utilities.USER_TASK_DETAIL_FIELDS)) {
            list = MainHelper.getUserTaskDetails(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST)) {
            list = MainHelper.getUserTaskDetailsCREDIT_CARD(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsHOME_LOANS(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh);
            userValidate();
        }  else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsCAR_LOANS(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HEALTH_LIST)) {
            list = MainHelper.getUserTaskDetailsHEALTH(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LIST)) {
            list = MainHelper.getUserTaskDetailsCAR(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_LIFE_LIST)) {
            list = MainHelper.getUserTaskDetailsLIFE(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_SAVING_LIST)) {
            list = MainHelper.getUserTaskDetailsSAVING(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_DEMAT_LIST)) {
            list = MainHelper.getUserTaskDetailsDEMAT(sh);
            userValidate();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_MORE_LIST)) {
            list = MainHelper.getUserTaskDetailsMORE(sh);
            userValidate();
        }


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


    private void userValidate() {

        LandingPage landingPage = new LandingPage();
        for (int i = 0; i < list.size(); i++) {
            if (value.equals(list.get(i).getProjectId())) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.eligibility.setText(Html.fromHtml(String.valueOf(list.get(i).getEligibility()), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.eligibility.setText(Html.fromHtml(String.valueOf(list.get(i).getEligibility())));
                }
//                binding.eligibility.setText(list.get(i).getEligibility());
            }
        }
    }
}