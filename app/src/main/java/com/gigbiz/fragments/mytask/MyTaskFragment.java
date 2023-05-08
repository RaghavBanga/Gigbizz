package com.gigbiz.fragments.mytask;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.OnClickListner;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.Utilities;
import com.gigbiz.activity.KycUploadActivity;
import com.gigbiz.activity.LandingPage;
import com.gigbiz.adapters.BusinessLoanAdapter;
import com.gigbiz.adapters.CarInsuranceAdapter;
import com.gigbiz.adapters.CarLoansAdapter;
import com.gigbiz.adapters.CreditCardAdapter;
import com.gigbiz.adapters.DematAccountAdapter;
import com.gigbiz.adapters.HealthInsuranceAdapter;
import com.gigbiz.adapters.HomeLoansAdapter;
import com.gigbiz.adapters.LifeInsuranceAdapter;
import com.gigbiz.adapters.MoreAdapter;
import com.gigbiz.adapters.PersonalLoansAdapter;
import com.gigbiz.adapters.SavingAccountAdapter;
import com.gigbiz.adapters.SliderAdapterDasBoard;
import com.gigbiz.adapters.TaskListAdapter;
import com.gigbiz.databinding.FragmentMytaskBinding;
import com.gigbiz.fragments.livetraining.LiveTrainingZoomFragment;
import com.gigbiz.helper.ItemOffsetDecoration;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.Banner;
import com.gigbiz.models.BannerResponse;
import com.gigbiz.models.KycRequestModel;
import com.gigbiz.models.KycResponseModel;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.Project;
import com.gigbiz.models.SliderData;
import com.gigbiz.models.SliderModel;
import com.gigbiz.models.TaskHomeModel;
import com.gigbiz.models.TaskModel;
import com.gigbiz.models.TransactionHistoryRequest;
import com.gigbiz.models.UserTaskRequest;
import com.gigbiz.models.UserTaskResponse;
import com.gigbiz.models.UserWalletDetailResponse;
import com.gigbiz.models.WalletDetail;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyTaskFragment extends Fragment {

    private FragmentMytaskBinding binding;
    //    List<TaskModel> list;
    List<TaskModel> projects;
    List<TaskModel> projects_credit;
    List<TaskModel> projects_personal_loan;
    List<TaskModel> projects_business_loan;
    List<TaskModel> projects_home_loan;
    List<TaskModel> projects_car_loan;
    List<TaskModel> projects_health;
    List<TaskModel> projects_car;
    List<TaskModel> projects_life;
    List<TaskModel> projects_saving;
    List<TaskModel> projects_demat;
    List<TaskModel> projects_more;
    SharedPrefManger sharedPrefManger;
    TaskListAdapter adapter;
    CreditCardAdapter creditCardAdapter;
    CarLoansAdapter carLoansAdapter;
    BusinessLoanAdapter businessLoanAdapter;
    CarInsuranceAdapter carInsuranceAdapter;
    DematAccountAdapter dematAccountAdapter;
    HealthInsuranceAdapter healthInsuranceAdapter;
    HomeLoansAdapter homeLoansAdapter;
    LifeInsuranceAdapter lifeInsuranceAdapter;
    MoreAdapter moreAdapter;
    PersonalLoansAdapter personalLoansAdapter;
    SavingAccountAdapter savingAccountAdapter;
    SharedPreferences sh;
    public boolean check = false;
    ArrayList<SliderData> sliderDataArrayList;
    private Handler sliderHandler = new Handler();
    public List<SliderModel> sliderItems;
    public String kycStatus;
    public String totalIncome = "";
    public String balance="";
    Menu menu;
    NavController navController;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyTaskViewModel homeViewModel =
                new ViewModelProvider(this).get(MyTaskViewModel.class);

        binding = FragmentMytaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        binding.progressbar.setVisibility(View.VISIBLE);

        setHasOptionsMenu(true);

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_home);

        navController = navHostFragment.getNavController();

//        list = new ArrayList<>();
        sliderDataArrayList = new ArrayList<>();
        sliderItems = new ArrayList<>();
        projects = new ArrayList<>();
        projects_credit = new ArrayList<>();
        projects_personal_loan = new ArrayList<>();
        projects_business_loan = new ArrayList<>();
        projects_home_loan = new ArrayList<>();
        projects_car_loan = new ArrayList<>();
        projects_health = new ArrayList<>();
        projects_car = new ArrayList<>();
        projects_life = new ArrayList<>();
        projects_saving = new ArrayList<>();
        projects_demat = new ArrayList<>();
        projects_more = new ArrayList<>();
        try {
            sharedPrefManger = new SharedPrefManger(getActivity(), sh);
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.creditlay.getVisibility() == View.VISIBLE) {
                    binding.creditlay.setVisibility(View.GONE);
                    binding.creditimg.setRotation(180f);
                } else {
                    binding.creditlay.setVisibility(View.VISIBLE);
                    binding.creditimg.setRotation(0f);
                }
            }
        });

        binding.sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.saleslay.getVisibility() == View.VISIBLE) {
                    binding.saleslay.setVisibility(View.GONE);
                    binding.salesimg.setRotation(180f);
                } else {
                    binding.saleslay.setVisibility(View.VISIBLE);
                    binding.salesimg.setRotation(0f);

                }
            }
        });
        binding.loans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.loansimg.animate().rotation(180).start();
                if (binding.loanslay.getVisibility() == View.VISIBLE) {
                    binding.loanslay.setVisibility(View.GONE);
                    binding.loansimg.setRotation(180f);
                } else {
                    binding.loanslay.setVisibility(View.VISIBLE);
                    binding.loansimg.setRotation(0f);
                }
            }
        });
        binding.insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.insurancelay.getVisibility() == View.VISIBLE) {
                    binding.insurancelay.setVisibility(View.GONE);
                    binding.insuranceimg.setRotation(180f);

                } else {
                    binding.insurancelay.setVisibility(View.VISIBLE);
                    binding.insuranceimg.setRotation(0f);

                }
            }
        });
        binding.accountOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                binding.accountOpenimg.animate().rotation(180).start();
                if (binding.accountlay.getVisibility() == View.VISIBLE) {
                    binding.accountlay.setVisibility(View.GONE);
                    binding.accountOpenimg.setRotation(180f);
                } else {
                    binding.accountlay.setVisibility(View.VISIBLE);
                    binding.accountOpenimg.setRotation(0f);
                }
            }
        });
        binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.morelay.getVisibility() == View.VISIBLE) {
                    binding.morelay.setVisibility(View.GONE);
                    binding.moreimg.setRotation(180f);
                } else {
                    binding.morelay.setVisibility(View.VISIBLE);
                    binding.moreimg.setRotation(0f);
                }
            }
        });

        setUpSliderHome();
        setUpFieldSalesRec();
        setUpCreditCardRec();
        setUpCarLoansRec();
        setUpBusinessLoanRec();
        setUpCarInsuranceRec();
        setUpMoreRec();
        setUpDematAccountRec();
        setUpHealthInsuranceRec();
        setUpHomeLoansRec();
        setUpLifeInsuranceRec();
        setUpPersonalLoansRec();
        setUpSavingAccountRec();

        binding.uploadKyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (kycStatus.equals("Active")) {
                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
//                        UploadKycFragment uploadKycFragment = new UploadKycFragment();
//                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.container_other_frag, uploadKycFragment);
//                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                        ft.addToBackStack(null);
//                        ft.commit();
                        Intent intent = new Intent(getActivity(), KycUploadActivity.class);
                        getActivity().startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.seeEarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new WalletDetail(totalIncome, balance));
                navController.navigate(R.id.earningsFragment);
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveTrainingZoomFragment dataDetailScreenFragment = new LiveTrainingZoomFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.container_other_frag, dataDetailScreenFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

//        binding.payout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ProjectPayoutsFragment projectPayoutsFragment = new ProjectPayoutsFragment();
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.add(R.id.container_other_frag, projectPayoutsFragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });

//        if (MainHelper.getUserDataList(sh).get(0).getType().equals(Utilities.TYPE_FREELANCER)){
//            getUserDataSignUp();
//        }else {
//            getUserData();
//        }

        getUserData();
        checkKycStatus();
//        if(sharedPrefManger.isFirstTimeSignup()){
//            sharedPrefManger.setFirstTimeSignup(false);
//            openKycDialog();
//        }


        getWalletDetail();

//        new AsyncTaskExample().execute();

        return root;
    }

    private void getWalletDetail() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<UserWalletDetailResponse> call = RetrofitClient.getInstance().getApi().getWalletRequest(
                        new TransactionHistoryRequest(userId, userToken));

                call.enqueue(new Callback<UserWalletDetailResponse>() {
                    @Override
                    public void onResponse(Call<UserWalletDetailResponse> call, Response<UserWalletDetailResponse> response) {

                        UserWalletDetailResponse userWalletDetailResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getApplicationContext(), userWalletDetailResponse.getRedeemList().get(0).getMsg(), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), userWalletDetailResponse.getRedeemList().get(0).getCurrentAmount(), Toast.LENGTH_SHORT).show();
//                                for (Redeem redeem:transactionHistoryResponse.getRedeemList()) {
//                                    redeemList.add(new Redeem(redeem.))
//                                }

                                totalIncome = userWalletDetailResponse.getRedeemList().get(0).getCurrentAmount();
                                balance = userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount();

                                check = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            } finally {
                                EventBus.getDefault().postSticky(new MessageEvent(userWalletDetailResponse.getRedeemList().get(0).getReferral_url()));

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            if (userWalletDetailResponse.getRedeemList() != null) {
                                                EventBus.getDefault().postSticky(new WalletDetail(totalIncome, balance));
                                                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                                                navigationView.getMenu().getItem(1).setTitle("Wallet("+String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount())+")");

                                                TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
                                                textView.setText(String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount()));

                                                binding.totalEarning.setText(String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getCurrentAmount()));
                                                binding.balance.setText(String.valueOf(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount()));
                                                int value = Integer.parseInt(userWalletDetailResponse.getRedeemList().get(0).getCurrentAmount().replaceAll("[^0-9]", ""));
                                                int value1 = Integer.parseInt(userWalletDetailResponse.getRedeemList().get(0).getRemaningAmount().replaceAll("[^0-9]", ""));
                                                binding.paidEarning.setText(String.valueOf("Rs. " + (value - value1)));
//                                                adapter.setNewList(redeemList);
//                                                binding.progressbar.setVisibility(View.GONE);

                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });

                            }

                            if (check == true) {
//                                sharedPrefManger.setListUserTaskDetails(transactionHistoryResponse.getProjects());

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<UserWalletDetailResponse> call, Throwable t) {

                    }
                });
            }
        });

        thread.start();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;

    }

    private void checkKycStatus() {
        String userId = MainHelper.getUserDataSignupList(sh).get(0).getUserId();
        String userToken = MainHelper.getUserDataSignupList(sh).get(0).getToken();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<KycResponseModel> call = RetrofitClient.getInstance().getApi().getKycResponseModel(
                        new KycRequestModel(userId, userToken));

                call.enqueue(new Callback<KycResponseModel>() {
                    @Override
                    public void onResponse(Call<KycResponseModel> call, Response<KycResponseModel> response) {

                        KycResponseModel userTaskResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getContext(), userTaskResponse.getKycstatus().get(0).getKycStatus(), Toast.LENGTH_SHORT).show();
                                check = true;
                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @SuppressLint("ResourceAsColor")
                                        @Override
                                        public void run() {
                                            try {
                                                kycStatus = userTaskResponse.getKycstatus().get(0).getKycStatus();
                                                if (userTaskResponse.getKycstatus().get(0).getKycStatus().equals("")) {
                                                    binding.kycStatus.setText("Not Submitted");
                                                    binding.kycStatus.setTextColor(R.color.red);
                                                } else {
                                                    binding.kycStatus.setText(userTaskResponse.getKycstatus().get(0).getKycStatus());
                                                }

//                                                if (kycStatus.equals("Active")) {
//                                                } else if (kycStatus.equals("Submitted")) {
//                                                } else if (kycStatus.equals("")) {
////                                                    if(!sharedPrefManger.isFirstTimeSignup()){
//                                                        openKycDialog();
////                                                    }
//                                                } else {
//
//                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    check = false;
//                            binding.progressbar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "user not approved or System Fail", Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            } finally {


                            }

                            if (check == true) {

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<KycResponseModel> call, Throwable t) {
//                binding.progressbar.setVisibility(View.GONE);
                    }
                });
            }
        });

        thread.start();

    }

    @Override
    public void onPause() {
        super.onPause();
        /**
         * image slider custom not in use without indicator
         * */
//        sliderHandler.removeCallbacks(sliderRunnable2);
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * image slider custom not in use without indicator
         * */
//        sliderHandler.postDelayed(sliderRunnable2, 5000);
    }

    /**
     * image slider custom not in use without indicator
     * */
//    private Runnable sliderRunnable2 = new Runnable() {
//        @Override
//        public void run() {
//            binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);
//
//        }
//    };

    private void setUpSliderHome() {
//        sliderDataArrayList.add(new SliderData(R.drawable.img1));
//        sliderDataArrayList.add(new SliderData(R.drawable.img2));
//        sliderDataArrayList.add(new SliderData(R.drawable.img3));
//        SliderHomeAdapter adapter = new SliderHomeAdapter(getContext(), sliderDataArrayList);
//        binding.Slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//        binding.Slider.setSliderAdapter(adapter);
//        binding.Slider.setScrollTimeInSec(3);
//        binding.Slider.setAutoCycle(true);
//        binding.Slider.startAutoCycle();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call<BannerResponse> call = RetrofitClient.getInstance().getApi().getBannerResponseModel();

                call.enqueue(new Callback<BannerResponse>() {
                    @Override
                    public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {

                        BannerResponse bannerResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getContext(), userTaskResponse.getKycstatus().get(0).getKycStatus(), Toast.LENGTH_SHORT).show();
                                check = true;
                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @SuppressLint("ResourceAsColor")
                                        @Override
                                        public void run() {
                                            try {

                                                for (Banner banner: bannerResponse.getBanner()) {
//                                                    sliderItems.add(new SliderModel(banner.getBannerImage()));
                                                    sliderDataArrayList.add(new SliderData(banner.getBannerImage()));

                                                }

                                                SliderAdapterDasBoard adapter = new SliderAdapterDasBoard(getContext(), sliderDataArrayList);
                                                binding.imageSlider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                                                binding.imageSlider.setSliderAdapter(adapter);
                                                binding.imageSlider.setScrollTimeInSec(3);
                                                binding.imageSlider.setAutoCycle(true);
                                                binding.imageSlider.startAutoCycle();
/**
 * image slider custom not in use without indicator
 * */
//                                                binding.viewPager2.setAdapter(new SliderAdapter(sliderItems, binding.viewPager2));
//
//                                                binding.viewPager2.setClipToPadding(false);
//                                                binding.viewPager2.setClipChildren(false);
//                                                binding.viewPager2.setOffscreenPageLimit(3);
//                                                binding.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//
//                                                CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
//                                                compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//                                                compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//                                                    @Override
//                                                    public void transformPage(@NonNull View page, float position) {
//                                                        float r = 1 - Math.abs(position);
//                                                        page.setScaleY(0.85f + r * 0.15f);
//                                                    }
//                                                });
//
//                                                binding.viewPager2.setPageTransformer(compositePageTransformer);
//
//                                                binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//                                                    @Override
//                                                    public void onPageSelected(int position) {
//                                                        super.onPageSelected(position);
//                                                        sliderHandler.removeCallbacks(sliderRunnable2);
//                                                        sliderHandler.postDelayed(sliderRunnable2, 5000); // slide duration 2 seconds
//                                                    }
////            @Override
////            public void onPageScrollStateChanged(int state) {
////
////                if (state == ViewPager.SCROLL_STATE_IDLE) {
////                    int index = binding.viewPager2.getCurrentItem();
////                    if ( index == 0 )
////                        binding.viewPager2.setCurrentItem( sliderItems.size() - 2, false );
////                    else if ( index == sliderItems.size() - 1 )
////                        binding.viewPager2.setCurrentItem( 1 , false);
////                }
////            }
//                                                });
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    check = false;
//                            binding.progressbar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "user not approved or System Fail", Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            } finally {


                            }

                            if (check == true) {

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<BannerResponse> call, Throwable t) {
//                binding.progressbar.setVisibility(View.GONE);
                    }
                });
            }
        });

        thread.start();


    }

    private void setUpSavingAccountRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListSaving.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListSaving.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListSaving.addItemDecoration(itemDecoration);
        savingAccountAdapter = new SavingAccountAdapter(projects_saving, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_saving.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_SAVING_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_saving.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_SAVING_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_saving.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_saving.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_SAVING_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_saving.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_SAVING_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_saving.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListSaving.setAdapter(savingAccountAdapter);
    }

    private void setUpPersonalLoansRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListPersonal.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListPersonal.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListPersonal.addItemDecoration(itemDecoration);
        personalLoansAdapter = new PersonalLoansAdapter(projects_personal_loan, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_personal_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_personal_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_personal_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_personal_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_personal_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_PERSONAL_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_personal_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListPersonal.setAdapter(personalLoansAdapter);
    }

    private void setUpLifeInsuranceRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListLife.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListLife.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListLife.addItemDecoration(itemDecoration);
        lifeInsuranceAdapter = new LifeInsuranceAdapter(projects_life, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_life.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_LIFE_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_life.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_LIFE_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_life.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_life.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_LIFE_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_life.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_LIFE_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_life.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListLife.setAdapter(lifeInsuranceAdapter);
    }

    private void setUpHomeLoansRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListHome.addItemDecoration(itemDecoration);
        homeLoansAdapter = new HomeLoansAdapter(projects_home_loan, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_home_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_home_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_home_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_home_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_home_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_HOME_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_home_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListHome.setAdapter(homeLoansAdapter);
    }

    private void setUpHealthInsuranceRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListHealth.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListHealth.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListHealth.addItemDecoration(itemDecoration);
        healthInsuranceAdapter = new HealthInsuranceAdapter(projects_health, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_health.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_HEALTH_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_health.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_HEALTH_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_health.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_health.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_HEALTH_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_health.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_HEALTH_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_health.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListHealth.setAdapter(healthInsuranceAdapter);
    }

    private void setUpDematAccountRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListDemat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListDemat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListDemat.addItemDecoration(itemDecoration);
        dematAccountAdapter = new DematAccountAdapter(projects_demat, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_demat.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_DEMAT_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_demat.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_DEMAT_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_demat.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_demat.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_DEMAT_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_demat.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_DEMAT_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_demat.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListDemat.setAdapter(dematAccountAdapter);
    }

    private void setUpMoreRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListmore.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListmore.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListmore.addItemDecoration(itemDecoration);
        moreAdapter = new MoreAdapter(projects_more, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_more.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_MORE_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_more.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_MORE_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_more.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_more.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_MORE_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_more.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_MORE_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_more.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListmore.setAdapter(moreAdapter);
    }

    private void setUpCarInsuranceRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListCarInsurance.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListCarInsurance.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListCarInsurance.addItemDecoration(itemDecoration);
        carInsuranceAdapter = new CarInsuranceAdapter(projects_car, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_car.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_CAR_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_car.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_CAR_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_car.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_car.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_CAR_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_car.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_CAR_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_car.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListCarInsurance.setAdapter(carInsuranceAdapter);
    }

    private void setUpBusinessLoanRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListBusiness.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListBusiness.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListBusiness.addItemDecoration(itemDecoration);
        businessLoanAdapter = new BusinessLoanAdapter(projects_business_loan, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_business_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_business_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_business_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_business_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_business_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_BUSINESS_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_business_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListBusiness.setAdapter(businessLoanAdapter);
    }

    private void setUpCarLoansRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListCarLoans.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListCarLoans.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListCarLoans.addItemDecoration(itemDecoration);
        carLoansAdapter = new CarLoansAdapter(projects_car_loan, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_car_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_car_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_car_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_car_loan.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_car_loan.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_CAR_LOANS_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_car_loan.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListCarLoans.setAdapter(carLoansAdapter);
    }

    private void setUpCreditCardRec() {
        List<TaskHomeModel> taskHomeModelList = new ArrayList<>();
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        taskHomeModelList.add(new TaskHomeModel("HDFC Bank", R.drawable.hdfc));
        taskHomeModelList.add(new TaskHomeModel("ICICI Bank", R.drawable.icici));
        taskHomeModelList.add(new TaskHomeModel("AXIS Bank", R.drawable.axis));
        taskHomeModelList.add(new TaskHomeModel("UCO Bank", R.drawable.union));
        try {
//            binding.rvListcredit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvListcredit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        binding.rvListcredit.addItemDecoration(itemDecoration);
        creditCardAdapter = new CreditCardAdapter(projects_credit, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
                try {
//                    EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                    Intent intent = new Intent(getActivity(), LandingPage.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_credit.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_credit.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_credit.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects_credit.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects_credit.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_CREDIT_CARD_LIST);
                        bundle.putString(Utilities.FORM_TYPE, projects_credit.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvListcredit.setAdapter(creditCardAdapter);
    }

    private void setUpFieldSalesRec() {
        try {
//            binding.rvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//            binding.rvList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.nav_header_vertical_spacing);
        binding.rvList.addItemDecoration(itemDecoration);
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false){
            @Override
            public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                lp.width = getWidth() / 3;
                return true;
            }
        });
        adapter = new TaskListAdapter(projects, new OnClickListner() {
            @Override
            public void onTaskItemClick(int position) {
//                Toast.makeText(getContext(), projects.get(0).getComp_name(), Toast.LENGTH_SHORT).show();
                try {
//                    Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("key", projects.get(position).getProjectid());
//                    bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                    intent.putExtras(bundle);
//                    getActivity().startActivity(intent);
                    if (kycStatus.equals("Active")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_FIELDS));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_FIELDS);
                        bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("Submitted")) {
                        EventBus.getDefault().postSticky(new AllListHandler(projects.get(position).getProjectid(),Utilities.USER_TASK_DETAIL_FIELDS));
                        Intent intent = new Intent(getActivity(), LandingPage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("key", projects.get(position).getProjectid());
                        bundle.putString(Utilities.TYPE_LIST, Utilities.USER_TASK_DETAIL_FIELDS);
                        bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
                        intent.putExtras(bundle);
                        getActivity().startActivity(intent);
//                        Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
                    } else if (kycStatus.equals("")) {
                        openKycDialog();
                    }
//                    else {
//                        EventBus.getDefault().postSticky(new MessageEvent(projects.get(position).getProjectid()));
//                        Intent intent = new Intent(getActivity(), LandingPage.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("key", projects.get(position).getProjectid());
//                        bundle.putString(Utilities.FORM_TYPE, projects.get(position).getHandler());
//                        intent.putExtras(bundle);
//                        getActivity().startActivity(intent);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        binding.rvList.setAdapter(adapter);
    }

    private void getUserDataSignUp() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataSignupList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataSignupList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataSignupList(sh).get(0).getType().toLowerCase();
                Call<UserTaskResponse> call = RetrofitClient.getInstance().getApi().getUserTaskResponse(
                        new UserTaskRequest(userId, userToken, "freelancer"));

                call.enqueue(new Callback<UserTaskResponse>() {
                    @Override
                    public void onResponse(Call<UserTaskResponse> call, Response<UserTaskResponse> response) {

                        UserTaskResponse userTaskResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getContext(), userTaskResponse.getProjects().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                for (Project project : userTaskResponse.getProjects()) {

                                    projects.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                            project.getLogo(), project.getTrainingType(), project.getProjectStatus()
                                            , "Rs " + project.getPriceStart() + "-" + " Rs " + project.getPriceEnd(), project.getHandler()));
                                }
                                check = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    check = false;
                                    binding.progressbar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "user not approved or System Fail", Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            } finally {

                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                adapter.setNewList(projects);
                                                binding.progressbar.setVisibility(View.GONE);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (check == true) {
                                sharedPrefManger.setListUserTaskDetails(userTaskResponse.getProjects());

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<UserTaskResponse> call, Throwable t) {
                        binding.progressbar.setVisibility(View.GONE);
                    }
                });
            }
        });

        thread.start();
    }

    private void getUserData() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<UserTaskResponse> call = RetrofitClient.getInstance().getApi().getUserTaskResponse(
                        new UserTaskRequest(userId, userToken, userType));

                call.enqueue(new Callback<UserTaskResponse>() {
                    @Override
                    public void onResponse(Call<UserTaskResponse> call, Response<UserTaskResponse> response) {

                        UserTaskResponse userTaskResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
//                                Toast.makeText(getContext(), userTaskResponse.getProjects().get(0).getMsg(), Toast.LENGTH_SHORT).show();
                                if (userTaskResponse.getProjects().size()!=0){
                                    for (Project project : userTaskResponse.getProjects()) {
                                        projects.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }
                                if (userTaskResponse.getCredit_card().size()!=0){
                                    for (Project project : userTaskResponse.getCredit_card()) {

                                        projects_credit.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }
                                if (userTaskResponse.getPersonal_loan().size()!=0){
                                    for (Project project : userTaskResponse.getPersonal_loan()) {

                                        projects_personal_loan.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }

                                }
                                if (userTaskResponse.getBusiness_loan().size()!=0){
                                    for (Project project : userTaskResponse.getBusiness_loan()) {

                                        projects_business_loan.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }

                                if (userTaskResponse.getHome_loan().size()!=0){
                                    for (Project project : userTaskResponse.getHome_loan()) {

                                        projects_home_loan.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }
                                if (userTaskResponse.getCar_loan().size()!=0){
                                    for (Project project : userTaskResponse.getCar_loan()) {

                                        projects_car_loan.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }
                                if (userTaskResponse.getHealth_insurance().size()!=0){
                                    for (Project project : userTaskResponse.getHealth_insurance()) {

                                        projects_health.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }
                                if (userTaskResponse.getCar_insurance().size()!=0){
                                    for (Project project : userTaskResponse.getCar_insurance()) {

                                        projects_car.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }
                                if (userTaskResponse.getLife_insurance().size()!=0){
                                    for (Project project : userTaskResponse.getLife_insurance()) {

                                        projects_life.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }

                                if (userTaskResponse.getSaving_account().size()!=0){
                                    for (Project project : userTaskResponse.getSaving_account()) {

                                        projects_saving.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }
                                if (userTaskResponse.getDemat_account().size()!=0){
                                    for (Project project : userTaskResponse.getDemat_account()) {

                                        projects_demat.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }

                                if (userTaskResponse.getMore_arr().size()!=0){
                                    for (Project project : userTaskResponse.getMore_arr()) {

                                        projects_more.add(new TaskModel(project.getProjectId(), project.getProjectTitle(), project.getCompName(),
                                                project.getLogo(), project.getProject_location(), project.getProjectStatus()
                                                , "Rs " + project.getProject_payout(), project.getHandler()));
                                    }
                                }

                                check = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    check = false;
                                    binding.progressbar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "user not approved or System Fail", Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            } finally {

                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                adapter.setNewList(projects);
                                                creditCardAdapter.setNewList(projects_credit);
                                                personalLoansAdapter.setNewList(projects_personal_loan);
                                                businessLoanAdapter.setNewList(projects_business_loan);
                                                homeLoansAdapter.setNewList(projects_home_loan);
                                                carLoansAdapter.setNewList(projects_car_loan);
                                                healthInsuranceAdapter.setNewList(projects_health);
                                                carInsuranceAdapter.setNewList(projects_car);
                                                lifeInsuranceAdapter.setNewList(projects_life);
                                                savingAccountAdapter.setNewList(projects_saving);
                                                dematAccountAdapter.setNewList(projects_demat);
                                                binding.progressbar.setVisibility(View.GONE);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (check == true) {
                                sharedPrefManger.setListUserTaskDetails(userTaskResponse.getProjects());
                                sharedPrefManger.setListUserTaskDetails_creditcard(userTaskResponse.getCredit_card());
                                sharedPrefManger.setListUserTaskDetails_personalloans(userTaskResponse.getPersonal_loan());
                                sharedPrefManger.setListUserTaskDetails_businessloans(userTaskResponse.getBusiness_loan());
                                sharedPrefManger.setListUserTaskDetails_homeloans(userTaskResponse.getHome_loan());
                                sharedPrefManger.setListUserTaskDetails_carloans(userTaskResponse.getCar_loan());
                                sharedPrefManger.setListUserTaskDetails_healthinsurance(userTaskResponse.getHealth_insurance());
                                sharedPrefManger.setListUserTaskDetails_carinsurance(userTaskResponse.getCar_insurance());
                                sharedPrefManger.setListUserTaskDetails_lifeinsurance(userTaskResponse.getLife_insurance());
                                sharedPrefManger.setListUserTaskDetails_savingaccount(userTaskResponse.getSaving_account());
                                sharedPrefManger.setListUserTaskDetails_demataccount(userTaskResponse.getDemat_account());
                                sharedPrefManger.setListUserTaskDetails_more(userTaskResponse.getMore_arr());

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<UserTaskResponse> call, Throwable t) {
//                        binding.progressbar.setVisibility(View.GONE);
                    }
                });
            }
        });

        thread.start();
    }

    public void openKycDialog(){
        Dialog dialog = new Dialog(getContext(), R.style.CustomAlertDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.kyc_dialog_box);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(((MainHelper.getWidth(getContext()) / 100) * 90), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        TextView redeemNow = dialog.findViewById(R.id.done);
        TextView cancel = dialog.findViewById(R.id.cancel);
        ImageView cancel_btn = dialog.findViewById(R.id.imageView2);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        redeemNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
//                UploadKycFragment uploadKycFragment = new UploadKycFragment();
//                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.container_other_frag, uploadKycFragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.addToBackStack(null);
//                ft.commit();
                Intent intent = new Intent(getActivity(), KycUploadActivity.class);
                getActivity().startActivity(intent);
            }
        });

//        if (kycStatus.equals("Active")) {
//            dialog.dismiss();
////            Toast.makeText(getContext(), "Your KYC is Already Approved", Toast.LENGTH_SHORT).show();
//        } else if (kycStatus.equals("Submitted")) {
//            dialog.dismiss();
////            Toast.makeText(getContext(), "Your KYC is Already Submitted", Toast.LENGTH_SHORT).show();
//        } else if (kycStatus.equals("")) {
////            openKycDialog();
//        }else {
//
//        }

        dialog.show();
    }

    private void doFirstRun() {
        if (sh.getBoolean("isFirstRun", true)) {
            openKycDialog();
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


//    private class AsyncTaskExample extends AsyncTask<Void, Void, List<TaskModel>> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(List<TaskModel> projects) {
//            super.onPostExecute(projects);
//
//            list = projects;
//
//
//        }
//
//        @Override
//        protected List<TaskModel> doInBackground(Void... voids) {
//
//
//            return projects;
//        }
//    }
}