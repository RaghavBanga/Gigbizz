package com.gigbiz.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gigbiz.databinding.ActivityGoodWorkFormScreenBinding;

public class GoodWorkFormScreen extends AppCompatActivity {

    AlertDialog.Builder alertDialogBuilder = null;
    AlertDialog alertDialog;
    private ActivityGoodWorkFormScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGoodWorkFormScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//
//        binding.swipeContainer.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        binding.webView.reload();
//                    }
//                }
//        );
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("No connection");

        alertDialogBuilder
                .setMessage("No connection, Retry")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                })
                .setCancelable(true);

        alertDialog = alertDialogBuilder.create();

        isNetworkConnected();

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            alertDialog.show();
            return false;
        } else{
            WebSettings webSettings = binding.webView.getSettings();

            binding.webView.setInitialScale(200);
            binding.webView.getSettings().setSupportZoom(true);
            binding.webView.getSettings().setLoadWithOverviewMode(true);
            binding.webView.getSettings().setBuiltInZoomControls(true);

            webSettings.setJavaScriptEnabled(true);
            binding.webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdQhHKxFaiCKoUriZoUn7ajmF0UKTEn04qycFNIEQ3J6klt-g/viewform?fbzx=1151289496204921461");
            binding.webView.setWebViewClient(new WebViewClient());

        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
//        finish();
//        onBackPressed();
        Intent intent = new Intent(GoodWorkFormScreen.this, HomeActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(GoodWorkFormScreen.this, HomeActivity.class);
//        startActivity(intent);
        if (binding.webView.canGoBack()){
            binding.webView.goBack();
        } else
            super.onBackPressed();
    }


}