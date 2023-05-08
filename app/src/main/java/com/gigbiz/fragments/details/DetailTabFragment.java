package com.gigbiz.fragments.details;

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

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.FragmentDetailTabBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.Project;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class DetailTabFragment extends Fragment {

    FragmentDetailTabBinding binding;
    List<Project> list;
    SharedPreferences sh;
    public String value,listtype;

    public DetailTabFragment() {
        // Required empty public constructor
    }

    public static DetailTabFragment newInstance(String param1, String param2) {
        DetailTabFragment fragment = new DetailTabFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailTabBinding.inflate(inflater, container, false);
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
//        Toast.makeText(getActivity(), event.message+"detail", Toast.LENGTH_SHORT).show();


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

        for (int i = 0; i < list.size(); i++) {
            if (value.equals(list.get(i).getProjectId())) {
                binding.compname.setText(list.get(i).getProjectTitle());
                binding.projectStatus.setText(list.get(i).getProjectStatus());
                binding.trainingType.setText(list.get(i).getProject_location());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.education.setText(Html.fromHtml(list.get(i).getProjectDescription(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.education.setText(Html.fromHtml(list.get(i).getProjectDescription()));
                }
//                binding.projectType.setText(list.get(i).getProjectType());
//                binding.time.setText(list.get(i).getStartTime() + "-" + list.get(i).getEndTime());
//                binding.price.setText("Rs " + list.get(i).getPriceStart() + "-" + " Rs " + list.get(i).getPriceEnd());
                Glide.with(getContext())
                        .load(list.get(i).getLogo())
                        .centerCrop()
                        .placeholder(R.drawable.logogigbiz)
                        .into(binding.logo);
            }
        }
    }
}