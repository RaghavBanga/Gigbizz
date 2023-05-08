package com.gigbiz.fragments.descriptiontab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gigbiz.adapters.DescriptionSubTabAdapter;
import com.gigbiz.databinding.FragmentDescriptionTabBinding;
import com.google.android.material.tabs.TabLayout;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;


public class DescriptionTabFragment extends Fragment {

    FragmentDescriptionTabBinding binding;


    public DescriptionTabFragment() {
        // Required empty public constructor
    }

    public static DescriptionTabFragment newInstance(String param1, String param2) {
        DescriptionTabFragment fragment = new DescriptionTabFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionTabBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        checkInternetConnection();
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Details"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Eligibility"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Steps to Follow"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("T&Cs"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("FAQ"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_START);

//        TabLayoutUtils.enableTabs(binding.tabLayout, false );

        final DescriptionSubTabAdapter adapter = new DescriptionSubTabAdapter(getActivity(), getChildFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(adapter);
//        binding.viewPager.setPagingEnabled(false);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //after removing this tabs will not work
                binding.viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }

    private void checkInternetConnection() {
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(
                getActivity(),
                getLifecycle()
        );

        DialogPropertiesPendulum properties = builder.getDialogProperties();

        properties.setConnectionCallback(new ConnectionCallback() { // Optional
            @Override
            public void hasActiveConnection(boolean hasActiveConnection) {
                // ...
            }
        });

        properties.setCancelable(false); // Optional
        properties.setNoInternetConnectionTitle("No Internet"); // Optional
        properties.setNoInternetConnectionMessage("Check your Internet connection and try again"); // Optional
        properties.setShowInternetOnButtons(true); // Optional
        properties.setPleaseTurnOnText("Please turn on"); // Optional
        properties.setWifiOnButtonText("Wifi"); // Optional
        properties.setMobileDataOnButtonText("Mobile data"); // Optional

        properties.setOnAirplaneModeTitle("No Internet"); // Optional
        properties.setOnAirplaneModeMessage("You have turned on the airplane mode."); // Optional
        properties.setPleaseTurnOffText("Please turn off"); // Optional
        properties.setAirplaneModeOffButtonText("Airplane mode"); // Optional
        properties.setShowAirplaneModeOffButtons(true); // Optional

        builder.build();
    }
}