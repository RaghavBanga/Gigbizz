package com.gigbiz.fragments.earningtab;

import static com.gigbiz.DatabaseHandler.SharedPrefManger.SHARED_PREF_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.Utilities;
import com.gigbiz.databinding.FragmentEarningTabBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.Project;
import com.gigbiz.models.WalletDetail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class EarningTabFragment extends Fragment {

    FragmentEarningTabBinding binding;
    List<Project> list;
    SharedPreferences sh;
    public String value,listtype;
    public String totalIncome, balance,referral_url;
    public boolean check = false;



    public EarningTabFragment() {
    }

    public static EarningTabFragment newInstance(String param1, String param2) {
        EarningTabFragment fragment = new EarningTabFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEarningTabBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        list = new ArrayList<>();
//        list = MainHelper.getUserTaskDetails(sh);

        binding.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    Uri uri = Uri.parse(String.valueOf(referral_url));
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("text/plain");
//                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GigBiz");
//                    String shareMessage= "\nHi\nDownload GigBiz application today and Earn Money on Completion of different task\nclick here to get it for android:-";
//                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
//                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
//                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GigBiz");
                    String shareMessage = "\nHi\nDownload GigBiz application today and Earn Money on Completion of different task\nclick here to get it for android:-";
                    shareMessage = shareMessage + referral_url + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return root;

    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onWalletDetailEvent(WalletDetail event) {
        totalIncome = event.total;
        balance = event.balance;

        binding.totalIncome.setText(String.valueOf(event.total));
        binding.banlance.setText(String.valueOf(event.balance));
        binding.balance.setText(String.valueOf(event.balance));
//        Toast.makeText(getActivity(), event.balance+"earning", Toast.LENGTH_SHORT).show();

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

    private void userValidate() {
        for (int i = 0; i < list.size(); i++) {
            if (value.equals(list.get(i).getProjectId())) {
                Glide.with(this)
                        .asBitmap()
                        .load(list.get(i).getBanner())
                        .into(binding.banner);

                if (list.get(i).getReferral_url()==null){
                    referral_url=" ";
                    binding.shareUrl.setText(" ");
                }else {
                    referral_url=list.get(i).getReferral_url();
                    binding.shareUrl.setText(list.get(i).getReferral_url());
                }
                break;
            }
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
}