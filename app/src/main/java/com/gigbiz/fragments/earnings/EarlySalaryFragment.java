package com.gigbiz.fragments.earnings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gigbiz.databinding.FragmentEarlySalaryBinding;

public class EarlySalaryFragment extends Fragment {
    FragmentEarlySalaryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEarlySalaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();










        return root;
    }
}