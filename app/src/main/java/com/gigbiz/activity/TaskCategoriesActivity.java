package com.gigbiz.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.databinding.ActivityTaskCategoriesBinding;

public class TaskCategoriesActivity extends AppCompatActivity {

    ActivityTaskCategoriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaskCategoriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        binding.btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(TaskCategoriesActivity.this, SelectionScreen.class);
//                intent.putExtra(Utilities.TYPE,type);
//                startActivity(intent);
//
//            }
//        });
    }
}