package com.gigbiz.fragments.goodworkerreportlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gigbiz.R;


public class ApprovedGoodWorkerFragment extends Fragment {


    public static ApprovedGoodWorkerFragment newInstance(String param1, String param2) {
        ApprovedGoodWorkerFragment fragment = new ApprovedGoodWorkerFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_approved_good_worker, container, false);
    }
}