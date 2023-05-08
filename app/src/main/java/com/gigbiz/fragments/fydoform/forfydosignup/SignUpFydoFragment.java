package com.gigbiz.fragments.fydoform.forfydosignup;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.databinding.FragmentSignUpFydoBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.MessageEvent;
import com.gigbiz.models.SignUpType;
import com.gigbiz.models.SubmitFydoFirstRequest;
import com.gigbiz.models.SubmitFydoFirstResponse;
import com.gigbiz.models.UserData;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFydoFragment extends Fragment {

    private SignUpFydoViewModel mViewModel;
    FragmentSignUpFydoBinding binding;
    public RadioButton radioButton;
    List<UserData> userData;
    private FormDashBoardActivity formDashBoardActivity;
    SharedPreferences sh;
    private boolean check = false;
    SharedPrefManger sharedPrefManger;
    String[] category = {"Variety store - 2- 5%", "Street Vendors - 10%",
            "Grocery - Kirana 2- 4%", "Restaurants & Cafes - dinner, lunch, snacks, coffee, tea, food 10-20%",
            "Stationery & Xerox - Print, xerox, photocopy, flex, pens, pencils, notebooks, file, spiral 4 - 5%",
            "Saloons And Parlour - barber, haircut, beard, threading, facial 10 - 20%",
            "Clothing - jeans, trousers, shirts, pants, dress, saree, suits - 5 - 15%",
            "Watches - clock, watch 3-10%", "Mobile Sellers - headphones, earphones, tempered glass, mobile accessories 1- 2%",
            "Mobile Accessories - 10-20 %", "Chemist - medicines, tablet, pharmacy 5 - 15%",
            "Sweet Shops - mithai, halwai, sweets, 3-10%", "ElectricaIs - light, fan, bulbs, motor, CFL, LED, wire 2-5%", "Footwear - shoes, sandals, flip-flops, sneakers, Pumps, Stilettos, heels, wedges, boots 5-15%",
            "Baby Care - 5-10%", "Desktop & Laptop shop - mouse, keyboard, speaker, headphones,earphones 2-5%",
            "Automobile Repair - garage, car, bike, repair, automobile 10-15%",
            "Automobile accessories - spare parts, bumper, headlight 2-10%",
            "Bakery & confectionery - bakeshop, cake, bread, brown bread 5-10%",
            "Bags Belts & Wallets - purse 5-10%",
            "Bathroom Fittings - home, bathroom accessories, shower 2-5%",
            "Bicycle - cycle, pump, tyre 2-8%", "Book Shops - copy,book, sticker, pen,pencil 5-10%", "Building Materials - cement,steel, bricks, stone 2-5%",
            "Cosmetics - makeup, lipstick, powder, chuna 5-10%", "Courier Services - mail, post 5%",
            "Cyber Cafe - cafe, internet, computers 5%", "Dairy Products - Milk, paneer, cold, cream, curd, dahi 3-6%",
            "Decorators - tent, marriage, birthday, function,DJ 10%",
            "Diagnostic Centre - x ray, fracture, test 10%", "Doctors Clinic - tooth, teeth,medicine specialist 10-20%",
            "Laundry or Dry cleaners - cleaner, iron, 10-15%",
            "Electronic shop - fridge, ac, tv, cooler 2-6%",
            "Florist - flowers,rose,jasmine, 5-10%",
            "Fruits & Vegetables - mango, brinjal, potato, water, 5-10 %",
            "Furnitures - table,chair,desk, dining, stool, 5-15%",
            "Gift Shops - 10%",
            "Gym-10%",
            "Helmets - 5%",
            "Home Appliances - fridge, ac, tv, cooler - 5%",
            "Ice cream parlor - ice,cream, cold,drink, pepsi, mazza, 3-10%",
            "Jewelry shop - gold,silver, ornaments, 1-3%",
            "Meat shop - mutton,chicken,egg 4-10%",
            "Marble & granite - home, floor 5-10%",
            "Musical instrument - casio, piano, guitar, drum 5-15%",
            "Flex printer - cards,print,type,pan card, aadhar card 5-10%",
            "Utility services - print,type,pan card, aadhar card 5-10%",
            "Optical - specs, goggles, lens 10-15%",
            "Photo Studio - marriage, photoshoot, image, camera, studio, passport 5-10%",
            "Packer & Mover - 5-10%",
            "Rental services - bike,car,hire 10-15%",
            "Sports Wear & Equipment - bat, hockey, badminton, ball, sports, emporium 5-10%",
            "Tailors - cloth, stiching 10%",
            "Tours & Travels - tour, holiday, tickets 5-10%",
            "Tyre Shops - ceat, MRF , tyre, tire 2-5%",
            "TOY STORE 5-10%",
            "VETERINARY 10%",
            "WINE SHOP 3-5%",
            "Distributor - 3-7%",
            "Pooja - 5%",
            "IT services - 10-15%",
            "Hardware - 3-7%",
            "Agro products - 2-5%",
            "Home decor - 5-10%",
            "Rental service - 10%",
            "Internal design/architect 10 -15%",
            "Paint shop - 2-5%",
            "Pest control - 5-10%",
            "Battery and inverter - 5-10%",
            "Kitchenware - 5-10%",
            "Event management - 10-15%",
            "Departmental stores - 5-10%",
            "Pet shops & clinic - 5%",
            "Real state - 10%"
    };
    String[] city = {"Ranchi", "Bhubaneswar",
            "Cuttack", "Jamshedpur"};

    String category_text,city_text;


    public static SignUpFydoFragment newInstance() {
        return new SignUpFydoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSignUpFydoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        sharedPrefManger = new SharedPrefManger(getContext(),getActivity().getSharedPreferences("gigbiz",MODE_PRIVATE));

        userData = new ArrayList<>();
        userData = MainHelper.getUserDataList(sh);

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.progressbar.setVisibility(View.VISIBLE);
                submitFirstReport(root);

            }
        });

        ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, category);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ShopCategoryCommission.setAdapter(ad);
        binding.ShopCategoryCommission.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category_text = category[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter ad1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, city);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.workingLocation.setAdapter(ad1);
        binding.workingLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city_text = city[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId1 = binding.radioGroup.getCheckedRadioButtonId();
                radioButton = root.findViewById(selectedId1);

                if (radioButton.getText().toString().equals("User (Customer)")){
                    binding.shopCategoryLay.setVisibility(View.GONE);
                    binding.shopNameLay.setVisibility(View.GONE);
                    binding.shopQwnerNameLay.setVisibility(View.GONE);
                    binding.shopQwnerMobLay.setVisibility(View.GONE);
                }else {
                    binding.shopCategoryLay.setVisibility(View.VISIBLE);
                    binding.shopNameLay.setVisibility(View.VISIBLE);
                    binding.shopQwnerNameLay.setVisibility(View.VISIBLE);
                    binding.shopQwnerMobLay.setVisibility(View.VISIBLE);
                }
//                if(radioButton.isChecked())
//                {
//                    radioButton.setChecked(false);
//                }
//                else
//                {
//                    radioButton.setChecked(true);
//                }
            }
        });

        getUserData();


        return root;
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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (radioButton.getText().toString().equals("User (Customer)")){
                    Call<SubmitFydoFirstResponse> call = RetrofitClient.getInstance().getApi().getFydoReportFirstResponse(
                            new SubmitFydoFirstRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                    MainHelper.getUserDataList(sh).get(0).getToken(),
                                    MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                    ,MainHelper.getUserDataList(sh).get(0).getProjectId(),
                                    radioButton.getText().toString(),
                                    null,
                                    null,
                                    null,
                                    null,
                                    city_text

                            ));

                    call.enqueue(new Callback<SubmitFydoFirstResponse>() {
                        @Override
                        public void onResponse(Call<SubmitFydoFirstResponse> call, Response<SubmitFydoFirstResponse> response) {
                            SubmitFydoFirstResponse submitFlipKartFirstResponse = response.body();
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
                                EventBus.getDefault().post(new SignUpType(radioButton.getText().toString()));


                            }


                        }

                        @Override
                        public void onFailure(Call<SubmitFydoFirstResponse> call, Throwable t) {
                            try {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }else {
                    Call<SubmitFydoFirstResponse> call = RetrofitClient.getInstance().getApi().getFydoReportFirstResponse(
                            new SubmitFydoFirstRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                    MainHelper.getUserDataList(sh).get(0).getToken(),
                                    MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                    ,MainHelper.getUserDataList(sh).get(0).getProjectId(),
                                    radioButton.getText().toString(),
                                    binding.ShopName.getText().toString().trim(),
                                    binding.ShopOwnerName.getText().toString().trim(),
                                    binding.ShopOwnerMob.getText().toString().trim(),
                                    category_text,
                                    city_text

                            ));

                    call.enqueue(new Callback<SubmitFydoFirstResponse>() {
                        @Override
                        public void onResponse(Call<SubmitFydoFirstResponse> call, Response<SubmitFydoFirstResponse> response) {
                            SubmitFydoFirstResponse submitFlipKartFirstResponse = response.body();
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
                                EventBus.getDefault().post(new SignUpType(radioButton.getText().toString()));


                            }


                        }

                        @Override
                        public void onFailure(Call<SubmitFydoFirstResponse> call, Throwable t) {
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
        mViewModel = new ViewModelProvider(this).get(SignUpFydoViewModel.class);
        // TODO: Use the ViewModel
    }

}