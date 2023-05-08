package com.gigbiz.fragments.myprofile;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.databinding.FragmentProfileBinding;
import com.gigbiz.fragments.idcardforprojects.IdCardForProjectsFragment;
import com.gigbiz.fragments.offerletter.OfferLetterFragment;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.OfferLetterResponseModel;
import com.gigbiz.models.ProfileDetailResponse;
import com.gigbiz.models.UpdateProfilePicRequest;
import com.gigbiz.models.UpdateProfilePicResponse;
import com.gigbiz.models.UserProfileRequest;
import com.gigbiz.models.UserTaskRequest;
import com.gigbiz.models.WalletDetail;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private boolean check = false;
    SharedPreferences sh;
    SharedPrefManger sharedPrefManger;
    File userPic_compressed;
    public String icard,offerletter ;
    public String totalIncome, balance, referral_url;
    Menu menu;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyProfileViewModel galleryViewModel =
                new ViewModelProvider(this).get(MyProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences(SharedPrefManger.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        try {
            sharedPrefManger = new SharedPrefManger(getActivity(), sh);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setHasOptionsMenu(true);

        binding.onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.popBackStack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.icard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (icard.equals("")){
//                    Toast.makeText(getContext(), "ID Card is Not Generated yet", Toast.LENGTH_SHORT).show();
//                }else {
//                    Uri uri = Uri.parse(icard); // missing 'http://' will cause crashed
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
//                }

                IdCardForProjectsFragment offerLetterFragment = new IdCardForProjectsFragment();
                offerLetterFragment.newInstance(offerletter);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.profile_fragment_container, offerLetterFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        binding.offerletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (offerletter!=null){
                    if (offerletter.equals("")){
                        Toast.makeText(getContext(), "OfferLetter is Not Generated yet", Toast.LENGTH_SHORT).show();
                    }else {
//                    Uri uri = Uri.parse(offerletter);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(intent);
                        OfferLetterFragment offerLetterFragment = new OfferLetterFragment();
                        offerLetterFragment.newInstance(offerletter, 0);
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.profile_fragment_container, offerLetterFragment);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }

            }
        });

        binding.userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1000);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_home);
        NavController navController = navHostFragment.getNavController();
        binding.goToMyTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.mytask);
            }
        });

        getProfileDetail();
        getOfferLetterUrl();
        return root;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onWalletDetailEvent(WalletDetail event) {
        totalIncome = event.total;
        balance = event.balance;

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setTitle("Wallet("+String.valueOf(balance)+")");

        TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
        textView.setText(String.valueOf(balance));

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        referral_url = event.message;

//        binding.referralUrl.setText(String.valueOf(event.message));

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

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        this.menu = menu;
//        getActivity().getMenuInflater().inflate(R.menu.home_other_option, menu);
        TextView textView = menu.findItem(R.id.wallet_t).getActionView().findViewById(R.id.rupees_main);
        textView.setText(String.valueOf(balance));
    }


    private void getProfileDetail() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<ProfileDetailResponse> call = RetrofitClient.getInstance().getApi().getProfileDetails(new
                        UserProfileRequest(userId, userToken, userType));

                call.enqueue(new Callback<ProfileDetailResponse>() {
                    @Override
                    public void onResponse(Call<ProfileDetailResponse> call, Response<ProfileDetailResponse> response) {
                        ProfileDetailResponse profileDetailResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
//                                if (loginResponses.getUserData().get(0).getMsg() == "successfully login") {
//                                formDashBoardActivity.report_id=otpSendResponse.getReportId();
                                check = true;
                                Toast.makeText(getContext(), String.valueOf(profileDetailResponse.getUserPic()), Toast.LENGTH_SHORT).show();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.name.setText(String.valueOf(profileDetailResponse.getName()));
                                        Glide.with(getContext())
                                                .load(profileDetailResponse.getUserPic())
                                                .centerCrop()
                                                .placeholder(R.drawable.employee)
                                                .into(binding.userPic);
                                        binding.address.setText(String.valueOf(profileDetailResponse.getAddress()));
                                        binding.phoneno.setText(String.valueOf(profileDetailResponse.getMobile()));
                                        binding.id.setText(String.valueOf(profileDetailResponse.getEmp_code()));
                                        binding.id1.setText(String.valueOf(profileDetailResponse.getEmp_code()));
                                        if (profileDetailResponse.getType().equals("freelancer")){
                                            binding.usertype.setText(String.valueOf("Agency Partner"));
                                        }else {
                                            binding.usertype.setText(String.valueOf("Gig Partner"));
                                        }
                                        icard = profileDetailResponse.getIcard();
                                    }
                                });

//                                offerletter= profileDetailResponse.getOfferletter();

                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                        }


                    }

                    @Override
                    public void onFailure(Call<ProfileDetailResponse> call, Throwable t) {
//                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        thread.start();
    }

    private void getOfferLetterUrl() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<OfferLetterResponseModel> call = RetrofitClient.getInstance().getApi().getOfferLetterResponseModel(new
                        UserTaskRequest(userId, userToken, userType));

                call.enqueue(new Callback<OfferLetterResponseModel>() {
                    @Override
                    public void onResponse(Call<OfferLetterResponseModel> call, Response<OfferLetterResponseModel> response) {
                        OfferLetterResponseModel profileDetailResponse = response.body();
                        if (response.isSuccessful()) {

                            if (profileDetailResponse.getOfferletterUrl().get(0).getStatus()==1){
                                try {
                                    offerletter= profileDetailResponse.getOfferletterUrl().get(0).getOfferletter();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    check = false;
                                    Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                                }
                            }else {
                                Toast.makeText(getContext(), profileDetailResponse.getOfferletterUrl().get(0).getMsg(), Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                        }


                    }

                    @Override
                    public void onFailure(Call<OfferLetterResponseModel> call, Throwable t) {
//                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        thread.start();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                if (requestCode == 1000) {
                    if (null != selectedImageUri) {

                        userPic_compressed = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(userPic_compressed.getAbsolutePath())
                                .into(binding.userPic);
//                        Toast.makeText(getContext(), userPic_compressed.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                        updateProfileDetail();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateProfileDetail() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
                String userType = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<UpdateProfilePicResponse> call = RetrofitClient.getInstance().getApi().getUpdateProfilePicResponse(new
                        UpdateProfilePicRequest(userId, userToken, userType,
                        MainHelper.getFileToByte(userPic_compressed.getAbsolutePath())));

                call.enqueue(new Callback<UpdateProfilePicResponse>() {
                    @Override
                    public void onResponse(Call<UpdateProfilePicResponse> call, Response<UpdateProfilePicResponse> response) {
                        UpdateProfilePicResponse updateProfilePicResponse = response.body();
                        if (response.isSuccessful()) {
                            try {

                                check = true;
                                Toast.makeText(getContext(), updateProfilePicResponse.getMsg(), Toast.LENGTH_SHORT).show();

//                                Glide.with(getContext())
//                                        .load(profileDetailResponse.getUserPic())
//                                        .centerCrop()
//                                        .placeholder(R.drawable.employee)
//                                        .into(binding.userPic);

                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (check == true) {
//                            sharedPrefManger.setListUserData(loginResponses.getUserData());
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));


                        }


                    }

                    @Override
                    public void onFailure(Call<UpdateProfilePicResponse> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        thread.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}