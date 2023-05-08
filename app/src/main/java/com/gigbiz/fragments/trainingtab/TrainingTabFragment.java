package com.gigbiz.fragments.trainingtab;

import static com.gigbiz.DatabaseHandler.SharedPrefManger.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.Utilities;
import com.gigbiz.adapters.YoutubeRecyclerAdapter;
import com.gigbiz.databinding.FragmentTrainingTabBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.Project;
import com.gigbiz.models.YoutubeVideo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class TrainingTabFragment extends Fragment {

    FragmentTrainingTabBinding binding;
    YoutubeRecyclerAdapter mRecyclerAdapter;
    List<Project> list;
    SharedPreferences sh;
    public String value,listtype;
//    String[] courses = {"10AM-11AM", "1PM-2PM"};

    public TrainingTabFragment() {
        // Required empty public constructor
    }


    public static TrainingTabFragment newInstance(String param1, String param2) {
        TrainingTabFragment fragment = new TrainingTabFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrainingTabBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        setUpSpinnerForTime();
        list = new ArrayList<>();
//        list = MainHelper.getUserTaskDetails(sh);

        return root;
    }

    private void setUpSpinnerForTime() {
//        binding.coursesspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(),
//                                courses[i],
//                                Toast.LENGTH_LONG)
//                        .show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, courses);
//
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.coursesspinner.setAdapter(ad);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        value = event.message;
        listtype = event.list_type;
//        Toast.makeText(getActivity(), event.message+"Earning", Toast.LENGTH_SHORT).show();

        if (listtype.equals(Utilities.USER_TASK_DETAIL_FIELDS)) {
            list = MainHelper.getUserTaskDetails(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST)) {
            list = MainHelper.getUserTaskDetailsCREDIT_CARD(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsPERSONAL_LOANS(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsHOME_LOANS(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsBUSINESS_LOANS(sh);
            prepareList();
        }  else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST)) {
            list = MainHelper.getUserTaskDetailsCAR_LOANS(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_HEALTH_LIST)) {
            list = MainHelper.getUserTaskDetailsHEALTH(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_CAR_LIST)) {
            list = MainHelper.getUserTaskDetailsCAR(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_LIFE_LIST)) {
            list = MainHelper.getUserTaskDetailsLIFE(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_SAVING_LIST)) {
            list = MainHelper.getUserTaskDetailsSAVING(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_DEMAT_LIST)) {
            list = MainHelper.getUserTaskDetailsDEMAT(sh);
            prepareList();
        } else if (listtype.equals(Utilities.USER_TASK_DETAIL_MORE_LIST)) {
            list = MainHelper.getUserTaskDetailsMORE(sh);
            prepareList();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

//        List<YoutubeVideo> youtubeVideos = prepareList();
        if (prepareList().size()!=0){
            mRecyclerAdapter = new YoutubeRecyclerAdapter(prepareList());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            binding.videosRec.setLayoutManager(mLayoutManager);
            binding.videosRec.setItemAnimator(new DefaultItemAnimator());
            binding.videosRec.setAdapter(mRecyclerAdapter);
        }

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private List<YoutubeVideo> prepareList() {

        ArrayList<YoutubeVideo> videoArrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //todo have to work here on api for getting training video
            if (value.equals(list.get(i).getProjectId())) {
                if (!list.get(i).getTraining().isEmpty()) {
                    for (int j = 0; j < list.get(i).getTraining().size(); j++) {

                        videoArrayList.add(new YoutubeVideo(list.get(i).getTraining().get(j).getTitle(),
                                list.get(i).getTraining().get(j).getVideoUrl(), "http://img.youtube.com/vi/" + list.get(i).getTraining().get(j).getVideoUrl() + "/0.jpg"));
                    }
                }
            }
        }

        return videoArrayList;
    }



//    private List<YoutubeVideo> prepareList() {
//        ArrayList<YoutubeVideo> videoArrayList=new ArrayList<>();
//        // add first item
//        YoutubeVideo video1 = new YoutubeVideo();
//        video1.setId(1l);
//        video1.setImageUrl("https://i.ytimg.com/vi/zI-Pux4uaqM/maxresdefault.jpg");
//        video1.setTitle(
//                "Thugs Of Hindostan - Official Trailer | Amitabh Bachchan | Aamir Khan");
//        video1.setVideoId("zI-Pux4uaqM");
//        videoArrayList.add(video1);
//        // add second item
//        YoutubeVideo video2 = new YoutubeVideo();
//        video2.setId(2l);
//        video2.setImageUrl("https://i.ytimg.com/vi/8ZK_S-46KwE/maxresdefault.jpg");
//        video2.setTitle(
//                "Colors for Children to Learning with Baby Fun Play with Color Balls Dolphin...");
//        video2.setVideoId("8ZK_S-46KwE");
//        // add third item
//        YoutubeVideo video3 = new YoutubeVideo();
//        video3.setId(3l);
//        video3.setImageUrl("https://i.ytimg.com/vi/8czMWUH7vW4/hqdefault.jpg");
//        video3.setTitle("Air Hostess Accepts Marriage Proposal Mid-Air, Airline Fires her.");
//        video3.setVideoId("8czMWUH7vW4");
//        // add four item
//        YoutubeVideo video4 = new YoutubeVideo();
//        video4.setId(4l);
//        video4.setImageUrl("https://i.ytimg.com/vi/YrQVYEb6hcc/maxresdefault.jpg");
//        video4.setTitle("EXPERIMENT Glowing 1000 degree METAL BALL vs Gunpowder (100 grams)");
//        video4.setVideoId("YrQVYEb6hcc");
//        // add four item
//        YoutubeVideo video5 = new YoutubeVideo();
//        video5.setId(5l);
//        video5.setImageUrl("https://i.ytimg.com/vi/S84Fuo2rGoY/maxresdefault.jpg");
//        video5.setTitle("What happened after Jauhar of Padmavati");
//        video5.setVideoId("S84Fuo2rGoY");
//        videoArrayList.add(video1);
//        videoArrayList.add(video2);
//        videoArrayList.add(video3);
//        videoArrayList.add(video4);
//        return videoArrayList;
//    }
}