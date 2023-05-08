package com.gigbiz.fragments.mediacontenttab;

import static com.gigbiz.DatabaseHandler.SharedPrefManger.SHARED_PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.gigbiz.Utilities;
import com.gigbiz.adapters.SliderAdapter;
import com.gigbiz.databinding.FragmentMediaContentTabBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.Project;
import com.gigbiz.models.SliderModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MediaContentTabFragment extends Fragment {

    private FragmentMediaContentTabBinding binding;
    private Handler sliderHandler = new Handler();
    List<Project> list;
    SharedPreferences sh;
    public String value,listtype;
    public List<SliderModel> sliderItems;


    public MediaContentTabFragment() {
        // Required empty public constructor
    }

    public static MediaContentTabFragment newInstance(String param1, String param2) {
        MediaContentTabFragment fragment = new MediaContentTabFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMediaContentTabBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        list = new ArrayList<>();
        sliderItems = new ArrayList<>();

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

    private void userValidate() {
        for (int i = 0; i < list.size(); i++) {
            if (value.equals(list.get(i).getProjectId())) {
                for (int j = 0; j < list.get(i).getMedia().size(); j++) {
                    sliderItems.add(new SliderModel(list.get(i).getMedia().get(j).getImg1()));
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);


        setUpFirstSlider();
//        setUpSecondSlider();
//        setUpThirdSlider();
//        setUpFourthSlider();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable2);
//        sliderHandler.removeCallbacks(sliderRunnable3);
//        sliderHandler.removeCallbacks(sliderRunnable4);
//        sliderHandler.removeCallbacks(sliderRunnable5);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable2, 3000);
//        sliderHandler.postDelayed(sliderRunnable3, 5000);
//        sliderHandler.postDelayed(sliderRunnable4, 7000);
//        sliderHandler.postDelayed(sliderRunnable5, 6000);
    }


    private void setUpFirstSlider(){

        binding.viewPager2.setAdapter(new SliderAdapter(sliderItems,binding.viewPager2));

        binding.viewPager2.setClipToPadding(false);
        binding.viewPager2.setClipChildren(false);
        binding.viewPager2.setOffscreenPageLimit(3);
        binding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        binding.viewPager2.setPageTransformer(compositePageTransformer);

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable2);
                sliderHandler.postDelayed(sliderRunnable2, 3000); // slide duration 2 seconds
            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//                if (state == ViewPager.SCROLL_STATE_IDLE) {
//                    int index = binding.viewPager2.getCurrentItem();
//                    if ( index == 0 )
//                        binding.viewPager2.setCurrentItem( sliderItems.size() - 2, false );
//                    else if ( index == sliderItems.size() - 1 )
//                        binding.viewPager2.setCurrentItem( 1 , false);
//                }
//            }
        });
    }
//    private void setUpSecondSlider(){
//        binding.viewPager3.setAdapter(new SliderAdapter(sliderItems,binding.viewPager3));
//
//        binding.viewPager3.setClipToPadding(false);
//        binding.viewPager3.setClipChildren(false);
//        binding.viewPager3.setOffscreenPageLimit(3);
//        binding.viewPager3.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//
//        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleY(0.85f + r * 0.15f);
//            }
//        });
//
//        binding.viewPager3.setPageTransformer(compositePageTransformer);
//
//        binding.viewPager3.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                sliderHandler.removeCallbacks(sliderRunnable3);
//                sliderHandler.postDelayed(sliderRunnable3, 5000); // slide duration 2 seconds
//            }
////            @Override
////            public void onPageScrollStateChanged(int state) {
////
////                if (state == ViewPager.SCROLL_STATE_IDLE) {
////                    int index = binding.viewPager3.getCurrentItem();
////                    if ( index == 0 )
////                        binding.viewPager3.setCurrentItem( sliderItems.size() - 2, false );
////                    else if ( index == sliderItems.size() - 1 )
////                        binding.viewPager3.setCurrentItem( 1 , false);
////                }
////            }
//        });
//    }
//    private void setUpThirdSlider(){
//
//        binding.viewPager4.setAdapter(new SliderAdapter(sliderItems,binding.viewPager4));
//
//        binding.viewPager4.setClipToPadding(false);
//        binding.viewPager4.setClipChildren(false);
//        binding.viewPager4.setOffscreenPageLimit(3);
//        binding.viewPager4.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//
//        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleY(0.85f + r * 0.15f);
//            }
//        });
//
//        binding.viewPager4.setPageTransformer(compositePageTransformer);
//
//        binding.viewPager4.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                sliderHandler.removeCallbacks(sliderRunnable4);
//                sliderHandler.postDelayed(sliderRunnable4, 7000); // slide duration 2 seconds
//            }
////            @Override
////            public void onPageScrollStateChanged(int state) {
////
////                if (state == ViewPager.SCROLL_STATE_IDLE) {
////                    int index = binding.viewPager4.getCurrentItem();
////                    if ( index == 0 )
////                        binding.viewPager4.setCurrentItem( sliderItems.size() - 2, false );
////                    else if ( index == sliderItems.size() - 1 )
////                        binding.viewPager4.setCurrentItem( 1 , false);
////                }
////            }
//        });
//    }
//    private void setUpFourthSlider(){
//
//        binding.viewPager5.setAdapter(new SliderAdapter(sliderItems,binding.viewPager5));
//
//        binding.viewPager5.setClipToPadding(false);
//        binding.viewPager5.setClipChildren(false);
//        binding.viewPager5.setOffscreenPageLimit(3);
//        binding.viewPager5.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//
//        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r = 1 - Math.abs(position);
//                page.setScaleY(0.85f + r * 0.15f);
//            }
//        });
//
//        binding.viewPager5.setPageTransformer(compositePageTransformer);
//
//        binding.viewPager5.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                sliderHandler.removeCallbacks(sliderRunnable5);
//                sliderHandler.postDelayed(sliderRunnable5, 6000); // slide duration 2 seconds
//            }
////            @Override
////            public void onPageScrollStateChanged(int state) {
////
////                if (state == ViewPager.SCROLL_STATE_IDLE) {
////                    int index = binding.viewPager5.getCurrentItem();
////                    if ( index == 0 )
////                        binding.viewPager5.setCurrentItem( sliderItems.size() - 2, false );
////                    else if ( index == sliderItems.size() - 1 )
////                        binding.viewPager5.setCurrentItem( 1 , false);
////                }
////            }
//        });
//    }
    private Runnable sliderRunnable2 = new Runnable() {
        @Override
        public void run() {
            binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);

        }
    };
//    private Runnable sliderRunnable3 = new Runnable() {
//        @Override
//        public void run() {
//            binding.viewPager3.setCurrentItem(binding.viewPager3.getCurrentItem() + 1);
//
//        }
//    };
//    private Runnable sliderRunnable4 = new Runnable() {
//        @Override
//        public void run() {
//
//            binding.viewPager4.setCurrentItem(binding.viewPager4.getCurrentItem() + 1);
//
//        }
//    };
//    private Runnable sliderRunnable5 = new Runnable() {
//        @Override
//        public void run() {
//
//            binding.viewPager5.setCurrentItem(binding.viewPager5.getCurrentItem() + 1);
//        }
//    };

}